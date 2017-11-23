package cz.cvut.fjfi.ksi.dsj.trialbot;

import cz.cvut.fjfi.ksi.dsj.trialbot.dsl.BotScriptRunner;
import cz.cvut.fjfi.ksi.dsj.trialbot.dsl.ScenarioScriptRunner;
import cz.cvut.fjfi.ksi.dsj.trialbot.model.Bot;
import cz.cvut.fjfi.ksi.dsj.trialbot.model.Scenario;
import cz.cvut.fjfi.ksi.dsj.trialbot.simulation.SimulationState;
import cz.cvut.fjfi.ksi.dsj.trialbot.ui.MapCanvas;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;

public class TrialBot extends Application {

  private Scene scene;
  private Stage stage;
  private Label[] botLabels = new Label[2];
  private Label roundLabel;
  private MapCanvas mapCanvas;

  private Scenario scenario;
  private Bot bots[] = new Bot[2];
  private SimulationState simulationState;


  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void init() throws Exception {
    super.init();

    if (getParameters().getRaw().size() < 1)
      throw new IllegalArgumentException("No scenario definition file specified.");

    String scenarioDefinitionFile = getParameters().getRaw().get(0);
    ScenarioScriptRunner scenarioScript = new ScenarioScriptRunner(scenarioDefinitionFile);

    scenario = scenarioScript.run();
    scenario.loadFromFile();

    BotScriptRunner r1ScriptRun = new BotScriptRunner(getParameters().getRaw().get(1));
    BotScriptRunner r2ScriptRun = new BotScriptRunner(getParameters().getRaw().get(2));

    bots[0] = r1ScriptRun.run();
    bots[1] = r2ScriptRun.run();

    simulationState = new SimulationState(bots, scenario);
  }

  @Override
  public void start(Stage stage) throws Exception {
    this.stage = stage;
    VBox root = new VBox(10);
    root.setPadding(new Insets(10));
    root.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
    this.scene = new Scene(root);

    VBox infoPane = new VBox();
    infoPane.setPadding(new Insets(10));
    HBox mapInfo = new HBox(20);
    Label mapNameLabel = new Label("Map: " + scenario.getName());
    mapNameLabel.setTextFill(Color.LIGHTGREY);
    roundLabel = new Label("");
    roundLabel.setTextFill(Color.LIGHTGREY);
    mapInfo.getChildren().addAll(mapNameLabel, roundLabel);
    infoPane.getChildren().add(mapInfo);

    AnchorPane botsPane = new AnchorPane();
    botLabels[0] = new Label("");
    botLabels[0].setTextFill(Color.GREENYELLOW);
    botLabels[1] = new Label("");
    botLabels[1].setTextFill(Color.ORANGERED);
    botsPane.getChildren().addAll(botLabels);
    AnchorPane.setLeftAnchor(botLabels[0], 20d);
    AnchorPane.setRightAnchor(botLabels[1], 20d);
    infoPane.getChildren().add(botsPane);
    root.getChildren().add(infoPane);

    Border b = new Border(new BorderStroke(Color.DARKGREY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
    infoPane.setBorder(b);

    mapCanvas = new MapCanvas(simulationState);
    root.getChildren().add(mapCanvas);

    stage.setTitle("TrialBot");
    stage.setScene(scene);
    stage.show();

    Thread t = new Thread(() -> {
      while (this.stage.isShowing() && simulationState.isRunning()) {
        try {
          if (simulationState.getWinners().size() > 0 || simulationState.getRound() == scenario.getRounds()) {
            Platform.runLater(this::showResult);
            return;
          } else {
            simulationState.nextRound();
            Platform.runLater(this::redrawUI);
            synchronized (this) {
              wait(scenario.getPause());
            }
          }
        } catch (Throwable e) {
          e.printStackTrace();
          return;
        }
      }
    });
    t.start();
  }

  private void redrawUI() {
    botLabels[0].setText("Bot 1: " + bots[0].getName()
        + " HP: [" + constructHpString(simulationState.getCurrentFrame().getHp(bots[0])) + "]");
    botLabels[1].setText("Bot 2: " + bots[1].getName()
        + " HP: [" + constructHpString(simulationState.getCurrentFrame().getHp(bots[1])) + "]");
    roundLabel.setText("Round: " + simulationState.getRound());

    mapCanvas.draw();
  }

  private void showResult() {
    Alert dialog = new Alert(Alert.AlertType.INFORMATION);
    dialog.setTitle("Simulation finished.");
    dialog.setHeaderText("Simulation completed in " + simulationState.getRound()
        + "/" + scenario.getRounds() + " rounds.");
    List<Bot> winners = simulationState.getWinners();
    if (winners.size() > 0) {
      StringBuilder winnersLine = new StringBuilder();
      for (Bot bot : winners) {
        if (winnersLine.length() != 0)
          winnersLine.append(", ");
        winnersLine.append(bot.getName());
      }
      dialog.setContentText("Winners are: " + winnersLine);
    }
    else
      dialog.setContentText("There are no winners.");

    dialog.show();
  }

  private String constructHpString(int hp) {
    switch (hp) {
      case 1:
        return "0__";
      case 2:
        return "00_";
      case 3:
        return "000";
    }

    return "___";
  }
}

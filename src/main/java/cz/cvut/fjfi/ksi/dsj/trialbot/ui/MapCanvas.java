package cz.cvut.fjfi.ksi.dsj.trialbot.ui;

import cz.cvut.fjfi.ksi.dsj.trialbot.model.Bot;
import cz.cvut.fjfi.ksi.dsj.trialbot.model.Position;
import cz.cvut.fjfi.ksi.dsj.trialbot.model.Scenario;
import cz.cvut.fjfi.ksi.dsj.trialbot.simulation.SimulationState;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class MapCanvas extends Canvas {

  public static final int TILE_SIZE = 32;

  private final SimulationState state;
  private final Scenario scenario;

  public MapCanvas(SimulationState state) {
    super(state.getScenario().getWidth() * TILE_SIZE, state.getScenario().getHeight() * TILE_SIZE);
    this.state = state;
    this.scenario = state.getScenario();
  }

  public void draw() {
    GraphicsContext gc = getGraphicsContext2D();
    gc.clearRect(0, 0, getWidth(), getHeight());

    for (int y = 0; y < scenario.getHeight(); y++) {
      for (int x = 0; x < scenario.getWidth(); x++) {
        char c = scenario.getMapDef()[y][x];
        if (c == '#')
          drawWall(gc, x, y);
        else if (c == '1')
          drawStartingPoint(gc, x, y, "1", Color.GREENYELLOW);
        else if (c == '2')
          drawStartingPoint(gc, x, y, "2", Color.ORANGERED);
        else if (c == 'F')
          drawFinishPoint(gc, x, y);
      }
    }

    for (int i = 0; i < state.getBots().length; i++) {
      Bot bot = state.getBots()[i];
      Position p = state.getCurrentFrame().getPosition(bot);
      drawBot(gc, p.getX(), p.getY(), i == 0 ? Color.GREENYELLOW : Color.ORANGERED);
    }

    gc.setFill(Color.DARKGREEN);
    gc.fill();
  }

  private void drawWall(GraphicsContext gc, int x, int y) {
    gc.setFill(Color.DARKGREEN);
    gc.rect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
  }

  private void drawStartingPoint(GraphicsContext gc, int x, int y, String number, Paint color) {
    gc.setFill(color);
    gc.setStroke(color);
    gc.strokeOval(x * TILE_SIZE + 2.5, y * TILE_SIZE + 2.5, TILE_SIZE - 5, TILE_SIZE - 5);
    gc.fillText(number, x * TILE_SIZE + TILE_SIZE / 2.6, y * TILE_SIZE + TILE_SIZE / 1.5);
  }

  public void drawBot(GraphicsContext gc, int x, int y, Paint color) {
    gc.setFill(color);
    gc.fillOval(x * TILE_SIZE + 2.5, y * TILE_SIZE + 2.5, TILE_SIZE - 5, TILE_SIZE - 5);
    gc.setFill(Color.BLACK);
    gc.fillText("B", x * TILE_SIZE + TILE_SIZE / 2.6, y * TILE_SIZE + TILE_SIZE / 1.5);
  }

  public void drawFinishPoint(GraphicsContext gc, int x, int y) {
    gc.setFill(Color.LIGHTBLUE);
    gc.setStroke(Color.LIGHTBLUE);
    gc.strokeOval(x * TILE_SIZE + 2.5, y * TILE_SIZE + 2.5, TILE_SIZE - 5, TILE_SIZE - 5);
    gc.fillOval(x * TILE_SIZE + 10, y * TILE_SIZE + 10, TILE_SIZE - 20, TILE_SIZE - 20);
  }
}

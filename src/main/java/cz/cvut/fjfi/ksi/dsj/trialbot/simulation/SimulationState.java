package cz.cvut.fjfi.ksi.dsj.trialbot.simulation;

import cz.cvut.fjfi.ksi.dsj.trialbot.model.*;

import java.util.ArrayList;
import java.util.List;

public class SimulationState {

  public static int MAX_HP = 3;

  private final Bot[] bots;
  private final Scenario scenario;
  private int round;
  private boolean running;
  private List<SimulationFrame> frames = new ArrayList<>();
  List<Command> commands = new ArrayList<>();

  public SimulationState(Bot[] bots, Scenario scenario) {
    this.round = 0;
    this.bots = bots;
    this.scenario = scenario;
    this.running = true;

    SimulationFrame initial = new SimulationFrame();

    for (Bot bot : bots)
      initial.setHp(bot, MAX_HP);

    for (int i = 0; i < bots.length; i++)
      initial.setPosition(bots[i], scenario.getStarts()[i]);

    this.frames.add(initial);

  }

  public SimulationFrame getCurrentFrame() {
    return frames.get(round);
  }

  public List<Bot> getWinners() {
    List<Bot> winners = new ArrayList<>(bots.length);
    for (Bot bot : bots) {
      Position pos = getCurrentFrame().getPosition(bot);
      if (scenario.isFinish(pos))
        winners.add(bot);
    }
    return winners;
  }

  public void nextRound() {
    commands.clear();
    SimulationFrame next = getCurrentFrame().copy();

    // simulate bots
    for (Bot bot : bots) {

      bot.reactOn(Event.TURN, this);

      next.addCurrentPositionsToBeen();
    }


    for (Command cmd : commands)
      cmd.execute(this, next);

    frames.add(next);
    round++;
  }

  public int getRound() {
    return round;
  }


  public Scenario getScenario() {
    return scenario;
  }

  public Bot[] getBots() {
    return bots;
  }

  public void addCommand(Command cmd) {
    commands.add(cmd);
  }

  public boolean isRunning() {
    return running;
  }
}

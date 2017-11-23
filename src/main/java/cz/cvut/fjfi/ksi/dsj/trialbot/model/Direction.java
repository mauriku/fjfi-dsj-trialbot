package cz.cvut.fjfi.ksi.dsj.trialbot.model;

import cz.cvut.fjfi.ksi.dsj.trialbot.simulation.SimulationState;

public class Direction {

  private final int dx;
  private final int dy;
  private final SimulationState state;
  private final Bot bot;

  public static Direction left(SimulationState state, Bot bot) {
    return new Direction(-1, 0, state, bot);
  }

  public static Direction right(SimulationState state, Bot bot) {
    return new Direction(1, 0, state, bot);
  }

  public static Direction up(SimulationState state, Bot bot) {
    return new Direction(0, -1, state, bot);
  }

  public static Direction down(SimulationState state, Bot bot) {
    return new Direction(0, 1, state, bot);
  }

  private Direction(int dx, int dy, SimulationState state, Bot bot) {
    this.dx = dx;
    this.dy = dy;
    this.state = state;
    this.bot = bot;
  }

  public boolean isWall() {
    Position pos = state.getCurrentFrame().getPosition(bot);
    return state.getScenario().isWall(pos.getX() + dx, pos.getY() + dy);
  }

  public boolean isNotWall() {
    return !isWall();
  }

  public boolean isBeenTo() {
    Position pos = state.getCurrentFrame().getPosition(bot);
    return state.getCurrentFrame().beenTo(bot, new Position(pos.getX() + dx, pos.getY() + dy));
  }

  public boolean isNotBeenTo() {
    return !isBeenTo();
  }

  public int getDx() {
    return dx;
  }

  public int getDy() {
    return dy;
  }

  @Override
  public String toString() {
    return "(" + dx + "," + dy + ")";
  }
}

package cz.cvut.fjfi.ksi.dsj.trialbot.model;

import cz.cvut.fjfi.ksi.dsj.trialbot.simulation.SimulationState;
import groovy.lang.Closure;

public class Handler {

  private Event event;
  private Closure condition;
  private Closure action;

  public Handler() {
  }

  public Handler(Event event) {
    this.event = event;
  }

  public boolean shouldHandle(Bot bot, SimulationState state) {
    condition.setProperty("hp", state.getCurrentFrame().getHp(bot));
    Object result = condition.call();
    return result == null || result instanceof Boolean && (Boolean) result;
  }

  public void handle(Bot bot, SimulationState state) {
    if (!shouldHandle(bot, state))
      return;

    action.setProperty("hp", state.getCurrentFrame().getHp(bot));
    action.setProperty("LEFT", Direction.left(state, bot));
    action.setProperty("RIGHT", Direction.right(state, bot));
    action.setProperty("UP", Direction.up(state, bot));
    action.setProperty("DOWN", Direction.down(state, bot));
    action.setProperty("name", bot.getName());
    action.setProperty("bot", bot);
    action.setProperty("state", state);

    action.run();
  }

  public Event getEvent() {
    return event;
  }

  public void setEvent(Event event) {
    this.event = event;
  }

  public Closure getCondition() {
    return condition;
  }

  public void setCondition(Closure condition) {
    this.condition = condition;
  }

  public Closure getAction() {
    return action;
  }

  public void setAction(Closure action) {
    this.action = action;
  }
}

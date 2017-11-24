package cz.cvut.fjfi.ksi.dsj.trialbot.model;

import cz.cvut.fjfi.ksi.dsj.trialbot.simulation.SimulationState;

import java.util.HashMap;
import java.util.Map;

public class Bot {

  private String name;
  private final Map<Event, Handler> handlers;

  public Bot() {
    this.handlers = new HashMap<Event, Handler>();
  }

  public void setName(String name) {
    this.name = name;
  }

  public void reactOn(Event event, SimulationState state) {
    Handler handler = handlers.get(event);
    handler.handle(this, state);
  }

  public String getName() {
    return name;
  }

  public Map<Event, Handler> getHandlers() {
    return handlers;
  }
}

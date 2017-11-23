package cz.cvut.fjfi.ksi.dsj.trialbot.model;

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

  public String getName() {
    return name;
  }

  public Map<Event, Handler> getHandlers() {
    return handlers;
  }
}

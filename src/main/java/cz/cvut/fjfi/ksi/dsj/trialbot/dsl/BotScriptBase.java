package cz.cvut.fjfi.ksi.dsj.trialbot.dsl;

import cz.cvut.fjfi.ksi.dsj.trialbot.model.*;
import cz.cvut.fjfi.ksi.dsj.trialbot.simulation.SimulationState;
import groovy.lang.Closure;
import groovy.lang.Script;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class BotScriptBase extends Script {

  private Bot bot = new Bot();

  protected void name(String name) {
    bot.setName(name);
  }

  protected void on(Map<String, Closure> arguments) {

    Iterator<String> keyIt = arguments.keySet().iterator();
    Iterator<Closure> valIt = arguments.values().iterator();

    // TURN: {condition}
    Event event = Event.valueOf(keyIt.next());
    Closure condition = valIt.next();

    String doKw = keyIt.next();
    if (!"do".equalsIgnoreCase(doKw))
      throw new IllegalStateException("DO keyword expected.");

    Closure action = valIt.next();

    Handler handler = new Handler(event);
    handler.setCondition(condition);
    handler.setAction(action);

    bot.getHandlers().put(event, handler);
  }

  protected void go(Direction dir) {
    go(Collections.singletonList(dir));
  }

  protected void go(List<Direction> dir) {
    Bot bot = (Bot) getBinding().getProperty("bot");
    SimulationState state = (SimulationState) getBinding().getProperty("state");

    Command cmn = new GoCommand(bot, dir);
    state.addCommand(cmn);
  }

  public Bot getBot() {
    return bot;
  }
}

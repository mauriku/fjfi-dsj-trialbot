package cz.cvut.fjfi.ksi.dsj.trialbot.dsl;

import cz.cvut.fjfi.ksi.dsj.trialbot.model.Bot;
import groovy.lang.Script;

public abstract class BotScriptBase extends Script {

  private Bot bot = new Bot();

  protected void name(String name) {
    bot.setName(name);
  }

  public Bot getBot() {
    return bot;
  }
}

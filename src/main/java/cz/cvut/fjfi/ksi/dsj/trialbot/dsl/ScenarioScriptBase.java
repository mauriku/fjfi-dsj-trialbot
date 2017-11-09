package cz.cvut.fjfi.ksi.dsj.trialbot.dsl;

import cz.cvut.fjfi.ksi.dsj.trialbot.model.Scenario;
import groovy.lang.Script;


public abstract class ScenarioScriptBase extends Script {

  private Scenario scenario = new Scenario();

  public void name(String name) {
    scenario.setName(name);
  }

  public void map(String map) {
    scenario.setMap(map);
  }

  public void width(int width) {
    scenario.setWidth(width);
  }

  public void height(int height) {
    scenario.setHeight(height);
  }

  public void pause(int pause) {
    scenario.setPause(pause);
  }
}

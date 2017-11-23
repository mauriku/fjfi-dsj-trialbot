package cz.cvut.fjfi.ksi.dsj.trialbot.dsl;

import cz.cvut.fjfi.ksi.dsj.trialbot.model.Scenario;
import cz.cvut.fjfi.ksi.dsj.trialbot.utils.FileUtils;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import org.codehaus.groovy.control.CompilerConfiguration;

import java.io.IOException;

public class ScenarioScriptRunner {

  Binding binding = new Binding();
  GroovyShell shell;
  String scriptPath;

  public ScenarioScriptRunner(String scriptPath) {
    this.scriptPath = scriptPath;
    CompilerConfiguration cfg = new CompilerConfiguration();
    cfg.setScriptBaseClass("cz.cvut.fjfi.ksi.dsj.trialbot.dsl.ScenarioScriptBase");
    shell = new GroovyShell(cfg);
  }

  public Scenario run() throws IOException {

    String source = FileUtils.loadFile(scriptPath);
    ScenarioScriptBase script = (ScenarioScriptBase) shell.parse(source);
    script.run();
    return script.getScenario();
  }

  public static void main(String[] args) throws IOException {
    ScenarioScriptRunner runner = new ScenarioScriptRunner(args[0]);
    runner.run();
  }
}

package cz.cvut.fjfi.ksi.dsj.trialbot.dsl;

import cz.cvut.fjfi.ksi.dsj.trialbot.model.Bot;
import cz.cvut.fjfi.ksi.dsj.trialbot.utils.FileUtils;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.control.customizers.ImportCustomizer;

import java.io.IOException;

public class BotScriptRunner {

  Binding bind = new Binding();
  GroovyShell shell;
  private final String scriptPath;
  private final CompilerConfiguration cfg;

  public BotScriptRunner(String scriptPath) {
    this.scriptPath = scriptPath;

    cfg = new CompilerConfiguration();
    cfg.setScriptBaseClass("cz.cvut.fjfi.ksi.dsj.trialbot.dsl.BotScriptBase");

    ImportCustomizer ic = new ImportCustomizer();
    ic.addStaticStars("cz.cvut.fjfi.ksi.dsj.trialbot.model.Event");

    cfg.addCompilationCustomizers(ic);

    shell = new GroovyShell(bind, cfg);
  }
  
  public Bot run() {
    try {
      String source = FileUtils.loadFile(scriptPath);

      BotScriptBase script = (BotScriptBase) shell.parse(source);
      script.run();
      return script.getBot();

    } catch (IOException e) {
      throw new RuntimeException("Cannot run script " + scriptPath + ".", e);
    }
  }
}

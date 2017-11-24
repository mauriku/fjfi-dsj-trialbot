package cz.cvut.fjfi.ksi.dsj.trialbot.model;


import cz.cvut.fjfi.ksi.dsj.trialbot.simulation.SimulationFrame;
import cz.cvut.fjfi.ksi.dsj.trialbot.simulation.SimulationState;

import java.util.List;
import java.util.Random;

public class GoCommand implements Command {

  private final Random random = new Random();

  private Bot bot;
  private List<Direction> dir;

  public GoCommand(Bot bot, List<Direction> dir) {
    this.bot = bot;
    this.dir = dir;
  }

  @Override
  public void execute(SimulationState state, SimulationFrame next) {
    Position currentPosition = state.getCurrentFrame().getPosition(bot);
    Position newPosition;
    if (dir.size() == 1)
      newPosition = currentPosition.translate(dir.get(0));
    else
      newPosition = currentPosition.translate(dir.get(random.nextInt(dir.size())));

    if (!state.getScenario().isWall(newPosition))
      next.setPosition(bot, newPosition);
  }
}

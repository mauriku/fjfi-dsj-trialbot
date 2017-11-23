package cz.cvut.fjfi.ksi.dsj.trialbot.simulation;

import cz.cvut.fjfi.ksi.dsj.trialbot.model.Bot;
import cz.cvut.fjfi.ksi.dsj.trialbot.model.Position;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SimulationFrame {
  private Map<Bot, Integer> hp = new HashMap<>();
  private Map<Bot, Position> position = new HashMap<>();
  private Map<Bot, Set<Position>> been = new HashMap<>();

  public void setHp(Bot bot, int hp) {
    this.hp.put(bot, hp);
  }

  public int getHp(Bot bot) {
    return this.hp.get(bot);
  }

  public boolean beenTo(Bot bot, Position pos) {
    return been.get(bot) != null && been.get(bot).contains(pos);
  }

  public void setPosition(Bot bot, Position position) {
    this.position.put(bot, position);
  }

  public Position getPosition(Bot bot) {
    return this.position.get(bot);
  }

  public SimulationFrame copy() {
    SimulationFrame copy = new SimulationFrame();
    copy.hp = new HashMap<>(hp);
    copy.position = new HashMap<>(position);
    copy.been = new HashMap<>(been);
    return copy;
  }

  public void addCurrentPositionsToBeen() {
    for (Map.Entry<Bot, Position> pos : position.entrySet()) {
      if (!been.containsKey(pos.getKey()))
        been.put(pos.getKey(), new HashSet<>());
      been.get(pos.getKey()).add(pos.getValue());
    }
  }
}

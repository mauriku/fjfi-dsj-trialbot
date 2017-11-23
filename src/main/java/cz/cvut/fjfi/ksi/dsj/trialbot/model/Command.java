package cz.cvut.fjfi.ksi.dsj.trialbot.model;


import cz.cvut.fjfi.ksi.dsj.trialbot.simulation.SimulationFrame;
import cz.cvut.fjfi.ksi.dsj.trialbot.simulation.SimulationState;

public interface Command {

  void execute(SimulationState state, SimulationFrame next);
}

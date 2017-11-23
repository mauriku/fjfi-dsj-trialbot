package cz.cvut.fjfi.ksi.dsj.trialbot.model;

public class Handler {

  private Event event;

  public Handler() {
  }

  public Handler(Event event) {
    this.event = event;
  }

  public Event getEvent() {
    return event;
  }

  public void setEvent(Event event) {
    this.event = event;
  }
}

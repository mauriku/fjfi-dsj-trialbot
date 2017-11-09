package cz.cvut.fjfi.ksi.dsj.trialbot.model;

public class Scenario {

  private String name;
  private String map;
  private int width;
  private int height;
  private int pause;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMap() {
    return map;
  }

  public void setMap(String map) {
    this.map = map;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public int getPause() {
    return pause;
  }

  public void setPause(int pause) {
    this.pause = pause;
  }
}

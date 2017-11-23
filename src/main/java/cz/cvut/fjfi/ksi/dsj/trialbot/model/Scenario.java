package cz.cvut.fjfi.ksi.dsj.trialbot.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Scenario {

  private String name;
  private String map;
  private int width;
  private int height;
  private int pause;
  private int rounds;

  private char[][] mapDef;

  private Position[] starts = new Position[2];
  private Position finish;

  public String getName() {
    return name;
  }

  public boolean isWall(int x, int y) {
    return mapDef[y][x] == '#';
  }

  public boolean isWall(Position pos) {
    return isWall(pos.getX(), pos.getY());
  }

  public boolean isFinish(int x, int y) {
    return mapDef[y][x] == 'F';
  }

  public boolean isFinish(Position pos) {
    return isFinish(pos.getX(), pos.getY());
  }

  public void loadFromFile() throws IOException {
    this.mapDef = new char[height][width];

    List<String> lines = Files.readAllLines(Paths.get(map));

    if (height != lines.size())
      throw new IllegalStateException("Height of map differs from declared one [" + height + "].");

    for (int lineNo = 0; lineNo < lines.size(); lineNo++) {
      String line = lines.get(lineNo);
      line.getChars(0, line.length(), mapDef[lineNo], 0);

      int p1 = line.indexOf('1');
      int p2 = line.indexOf('2');
      int fp = line.indexOf('F');

      if (p1 != -1)
        starts[0] = new Position(p1, lineNo);
      if (p2 != -1)
        starts[1] = new Position(p2, lineNo);
      if (fp != -1)
        finish = new Position(fp, lineNo);
    }

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

  public Position[] getStarts() {
    return starts;
  }

  public Position getFinish() {
    return finish;
  }

  public char[][] getMapDef() {
    return mapDef;
  }

  public int getRounds() {
    return rounds;
  }

  public void setRounds(int rounds) {
    this.rounds = rounds;
  }
}

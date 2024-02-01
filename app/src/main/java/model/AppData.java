package model;

import utils.Variation;

public class AppData {
  private Variation variation = Variation.DEFAULT;
  private GameData gameData = new GameData();
  private ScoreBoard scoreBoard = new ScoreBoard();

  public AppData() {
  }

  public Variation getVariation() {
    return variation;
  }

  public void setVariation(Variation variation) {
    this.variation = variation;
    gameData.setVariation(variation);
  }

  public GameData getGameData() {
    return gameData;
  }

  public ScoreBoard getScoreBoard() {
    return scoreBoard;
  }
}

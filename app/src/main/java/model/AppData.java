package model;

public class AppData {
  private GameData gameData = new GameData();
  private ScoreBoard scoreBoard = new ScoreBoard();

  public AppData() {
  }

  public GameData getGameData() {
    return gameData;
  }

  public ScoreBoard getScoreBoard() {
    return scoreBoard;
  }
}

package view;

import java.util.List;

import model.GameData;
import model.ScoreBoardEntry;

public class ScoreBoardView extends View {
  private List<ScoreBoardEntry> scoreBoard;

  public ScoreBoardView(GameData gameData) {
    scoreBoard = gameData.getScoreBoard();
  }

  @Override
  public void display() {
    clearScreen();
    System.out.println("Score Board");
    System.out.println();

    int counter = 1;
    for (ScoreBoardEntry entry : scoreBoard) {
      System.out.println("Game #" + counter++);
      for (model.Player player : entry.getPlayers()) {
        System.out.println(player.getName() + ": " + player.getScoreCard().getTotalScore() + " points");
      }
      System.out.println();
    }
  }
}

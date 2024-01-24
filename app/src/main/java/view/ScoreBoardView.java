package view;

import java.util.List;

import model.GameData;
import model.ScoreBoardEntry;

public class ScoreBoardView implements View {
  private List<ScoreBoardEntry> scoreBoard;

  public ScoreBoardView(GameData gameData) {
    scoreBoard = gameData.getScoreBoard();
  }

  public void display() {
    System.out.println("====================================");
    System.out.println();

    System.out.println("Score Board");
    for (ScoreBoardEntry entry : scoreBoard) {
      for (model.Player player : entry.getPlayers()) {
        System.out.println(player.getName() + ": " + player.getScoreCard().getTotalScore() + " points");
      }
      System.out.println();
    }

    System.out.println("====================================");
  }
}

package view;

import java.util.List;

import model.AppData;
import model.ScoreBoardEntry;
import model.ScoreCard.strategies.Player;

public class ScoreBoardView extends View {
  private List<ScoreBoardEntry> scoreBoardEntries;

  public ScoreBoardView(AppData appData) {
    scoreBoardEntries = appData.getScoreBoard().getEntries();
  }

  @Override
  public void display() {
    clearScreen();
    System.out.println("Score Board");
    System.out.println();

    int counter = 1;
    for (ScoreBoardEntry entry : scoreBoardEntries) {
      System.out.println("Game #" + counter++);
      for (Player player : entry.getPlayers()) {
        System.out.println(player.getName() + ": " + player.getScoreCard().getTotalScore() + " points");
      }
      System.out.println();
    }
  }
}

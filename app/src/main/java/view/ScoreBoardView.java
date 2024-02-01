package view;

import java.util.List;

import model.AppData;
import model.Player;
import model.ScoreBoardEntry;

public class ScoreBoardView extends View {
  private List<ScoreBoardEntry> scoreBoardEntries;

  public ScoreBoardView(AppData appData) {
    scoreBoardEntries = appData.getScoreBoard().getEntries();
  }

  @Override
  public void display() {
    clearScreen();

    if (getState().equals("menu")) {
      displayMenu();
    } else {
      displayScoreBoard();
    }
  }

  private void displayMenu() {
    System.out.println();
  }

  private void displayScoreBoard() {
    System.out.println("Score Board");
    System.out.println();

    displayScores();
  }

  private void displayScores() {
    int counter = 1;
    for (ScoreBoardEntry entry : scoreBoardEntries) {
      System.out.println();
      System.out.println("Game #" + counter++);
      for (Player player : entry.getPlayers()) {
        String name = player.getName() + " (" + (player.isHuman() ? "Human" : "Computer") + ")";
        if (getState().equals("simplified")) {
          System.out.println(name + ": " + player.getScoreCard().getTotalScore() + " points");
        } else if (getState().equals("detailed")) {
          System.out.println(name);
          System.out.println();
          System.out.println(player.getScoreCard().toString());
        }
      }
      System.out.println();
      System.out.println("==================================");
    }
  }
}

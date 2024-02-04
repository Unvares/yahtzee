package view;

import java.util.List;
import model.AppData;
import model.Player;
import model.ScoreBoardEntry;

/**
 * This class represents the view for the Score Board.
 */
public class ScoreBoardView extends View {
  /**
   * The application data.
   */
  private AppData appData = AppData.getInstance();

  /**
   * Constructor for ScoreBoardView.
   */
  public ScoreBoardView() {
  }

  /**
   * This method displays the Score Board view.
   */
  @Override
  public void display() {
    clearScreen();

    if (getState().equals("menu")) {
      displayMenu();
    } else {
      displayScoreBoard();
    }
  }

  /**
   * This method displays the menu.
   */
  private void displayMenu() {
    System.out.println();
  }

  /**
   * This method displays the Score Board.
   */
  private void displayScoreBoard() {
    System.out.println("Score Board");
    System.out.println();

    displayScores();
  }

  /**
   * This method displays the scores of the players.
   */
  private void displayScores() {
    List<ScoreBoardEntry> scoreBoardEntries = appData.getScoreBoard().getEntries();

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

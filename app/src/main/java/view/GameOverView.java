package view;

import java.util.List;
import model.AppData;
import model.Player;

/**
 * This class represents the view for the game over screen.
 */
public class GameOverView extends View {
  /**
   * Constructor for GameOverView.
   */
  public GameOverView() {
  }

  /**
   * This method displays the game over screen with the final scores of all
   * players.
   */
  @Override
  public void display() {
    clearScreen();
    System.out.println("Game Over");
    System.out.println();

    List<Player> players = AppData.getInstance().getGameData().getPlayers();
    for (Player player : players) {
      System.out.println(player.getName() + ": " + player.getScoreCard().getTotalScore());
    }
    System.out.println();
  }
}

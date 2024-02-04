package view;

import java.util.List;

import model.AppData;
import model.GameData;
import model.Player;

/**
 * This class represents the view for the game.
 */
public class GameView extends View {
  /**
   * The game data.
   */
  private GameData gameData = AppData.getInstance().getGameData();

  /**
   * Constructor for GameView.
   */
  public GameView() {
  }

  /**
   * This method displays the game view.
   */
  @Override
  public void display() {
    clearScreen();
    List<Player> players = gameData.getPlayers();
    for (int i = 0; i < players.size(); i++) {
      Player player = players.get(i);
      if (i == gameData.getCurrentPlayerIndex()) {
        System.out.println(player.getName() + " (current player)");
      } else if (player.hasFilledScoreCard()) {
        System.out.println(player.getName() + " (completed game)");
      } else {
        System.out.println(player.getName());
      }
    }

    System.out.println();
    System.out.println("Current dice values: " + gameData.getCurrentDiceValues().toString());
    System.out.println();
  }

}

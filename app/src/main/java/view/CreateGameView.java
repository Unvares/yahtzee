package view;

import java.util.List;
import model.AppData;
import model.GameData;
import model.Player;

/**
 * This class represents the view for creating a game.
 */
public class CreateGameView extends View {
  /**
   * Constructor for CreateGameView.
   */
  public CreateGameView() {
  }

  /**
   * This method displays the list of players.
   */
  @Override
  public void display() {
    clearScreen();
    System.out.println("List of players:");

    GameData gameData = AppData.getInstance().getGameData();
    List<Player> players = gameData.getPlayers();
    for (int i = 0; i < players.size(); i++) {
      Player player = players.get(i);
      String playerType = player.isHuman() ? "Player" : "Computer";

      System.out.println((i + 1) + ". " + player.getName() + ", " + playerType);
    }
    System.out.println();
  }

}

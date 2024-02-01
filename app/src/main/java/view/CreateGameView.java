package view;

import java.util.List;

import model.GameData;
import model.Player;

public class CreateGameView extends View {
  private GameData gameData;

  public CreateGameView(GameData gameData) {
    this.gameData = gameData;
  }

  @Override
  public void display() {
    clearScreen();
    System.out.println("List of players:");

    List<Player> players = gameData.getPlayers();
    for (int i = 0; i < players.size(); i++) {
      Player player = players.get(i);
      String playerType = player.isHuman() ? "Player" : "Computer";

      System.out.println((i + 1) + ". " + player.getName() + ", " + playerType);
    }
    System.out.println();
  }

}

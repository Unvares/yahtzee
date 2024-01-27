package view;

import java.util.List;

import model.GameData;
import model.Player;

public class CreateGameView implements View {
  private GameData gameData;

  public CreateGameView(GameData gameData) {
    this.gameData = gameData;
  }

  @Override
  public void display() {
    System.out.println("====================================");
    System.out.println();

    System.out.println("List of players:");

    List<Player> players = gameData.getPlayers();
    for (int i = 0; i < players.size(); i++) {
      Player player = players.get(i);
      String playerType = player.isHuman() ? "Player" : "Computer";

      System.out.println((i + 1) + ". " + player.getName() + ", " + playerType);
    }

    System.out.println();
    if (players.size() < 10) {
      System.out.println("1. Create new character");
    }

    if (players.size() > 0) {
      System.out.println("2. Delete existing character");
    }

    if (players.size() >= 2) {
      System.out.println("3. Start game");
    }
    System.out.println("4. Return to main menu");

    System.out.println();
    System.out.println("====================================");
  }

}

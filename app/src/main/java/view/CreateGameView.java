package view;

import java.util.List;

import model.Player;
import model.Players;

public class CreateGameView implements View {
  private Players players;

  public CreateGameView(Players players) {
    this.players = players;
  }

  public void setPlayers(Players players) {
    this.players = players;
  }

  public void display() {
    System.out.println("====================================");
    System.out.println();

    System.out.println("List of players:");

    List<Player> players = this.players.getPlayers();
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

package view;

import java.util.List;

import model.GameData;
import model.Player;

public class GameView implements View {
  private GameData gameData;

  public GameView(GameData gameData) {
    this.gameData = gameData;
  }

  @Override
  public void display() {
    System.out.println("====================================");
    System.out.println();

    List<Player> players = gameData.getPlayers();
    for (int i = 0; i < players.size(); i++) {
      Player player = players.get(i);
      if (i == gameData.getCurrentPlayerIndex()) {
        System.out.println(player.getName() + " (current player)");
      } else {
        System.out.println(player.getName());
      }
    }

    System.out.println();
    System.out.println("Current dice values: " + gameData.getCurrentDiceValues().toString());
    System.out.println();

    if (gameData.canRoll()) {
      System.out.println("1. Roll dice");
    }
    if (gameData.canDeleteDices()) {
      System.out.println("2. Choose dices to discard");
    }
    System.out.println("3. View score card");
    if (gameData.canEndTurn()) {
      System.out.println("4. Score!");
    }
    System.out.println("5. Save & Quit");

    System.out.println();
    System.out.println("====================================");
  }

}

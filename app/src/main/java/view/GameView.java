package view;

import model.GameData;
import model.Player;
import model.Players;

public class GameView implements View {
  private GameData gameData;

  public GameView(GameData gameData) {
    this.gameData = gameData;
  }

  public void display() {
    System.out.println("====================================");
    System.out.println();

    Players players = gameData.getPlayers();
    for (int i = 0; i < players.getPlayers().size(); i++) {
      Player player = players.getPlayers().get(i);
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
    if (gameData.canEndTurn()) {
      System.out.println("3. End turn");
    }

    System.out.println();
    System.out.println("====================================");
  }

}

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
  }

}

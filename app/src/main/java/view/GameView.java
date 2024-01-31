package view;

import java.util.List;

import model.GameData;
import model.ScoreCard.strategies.Player;

public class GameView extends View {
  private GameData gameData;

  public GameView(GameData gameData) {
    this.gameData = gameData;
  }

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

package view;

import java.util.List;

import model.GameData;
import model.Player;

public class GameOverView implements View {
  private List<Player> players;

  public GameOverView(GameData gameData) {
    players = gameData.getPlayers();
  }

  @Override
  public void display() {
    System.out.println("Game Over");
    System.out.println();

    for (Player player : players) {
      System.out.println(player.getName() + ": " + player.getScoreCard().getTotalScore());
    }
    System.out.println();
  }
}

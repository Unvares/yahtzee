package view;

import java.util.List;

import model.AppData;
import model.Player;

public class GameOverView extends View {
  private List<Player> players = AppData.getInstance().getGameData().getPlayers();

  public GameOverView() {
  }

  @Override
  public void display() {
    clearScreen();
    System.out.println("Game Over");
    System.out.println();

    for (Player player : players) {
      System.out.println(player.getName() + ": " + player.getScoreCard().getTotalScore());
    }
    System.out.println();
  }
}

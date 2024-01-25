package controller;

import java.util.List;

import model.GameData;
import model.Player;
import model.ScoreBoardEntry;
import utils.InputHandler;
import utils.InputHandlerImpl;
import utils.State;
import view.GameOverView;
import view.View;

public class GameOverController implements Controller {
  private State state = State.GAME_OVER;
  private GameData gameData;
  private View view;

  public GameOverController(GameData gameData) {
    this.gameData = gameData;
    view = new GameOverView(gameData);
  }

  @Override
  public void run() {
    view.display();

    State newState = getChoice();

    while (newState == State.INVALID) {
      System.out.println("Invalid choice. Please try again.");
      newState = getChoice();
    }

    Router.pushState(newState);
  }

  @Override
  public State getControllerState() {
    return this.state;
  }

  public State getChoice() {
    InputHandler inputHandler = InputHandlerImpl.getInstance();
    inputHandler.getAnyInput("Press any button to return to the main menu: ");
    List<Player> players = gameData.getPlayers();
    for (Player player : players) {
      int score = player.getScoreCard().getTotalScore();
      player.setTotalScore(score);
    }
    gameData.addScoreBoardEntry(new ScoreBoardEntry(players));
    return State.MENU;
  }
}

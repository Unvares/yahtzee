package controller;

import java.util.List;

import model.GameData;
import model.Player;
import model.ScoreBoardEntry;
import utils.InputHandler;
import utils.State;
import view.GameOverView;

public class GameOverController extends Controller {
  private GameData gameData;

  public GameOverController(GameData gameData) {
    super(new GameOverView(gameData), State.GAME_OVER);
    this.gameData = gameData;
  }

  @Override
  protected State getNewState(InputHandler inputHandler) {
    inputHandler.getAnyInput("Press enter to return to the main menu: ");
    List<Player> players = gameData.getPlayers();
    gameData.addScoreBoardEntry(new ScoreBoardEntry(players));
    return State.MENU;
  }
}

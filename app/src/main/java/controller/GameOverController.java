package controller;

import java.util.List;

import model.AppData;
import model.GameData;
import model.Player;
import model.ScoreBoardEntry;
import utils.InputHandler;
import utils.ControllerName;
import view.GameOverView;

public class GameOverController extends Controller {
  private AppData appData = AppData.getInstance();
  private GameData gameData = appData.getGameData();

  public GameOverController() {
    super(new GameOverView());

  }

  @Override
  protected ControllerName getNewController(InputHandler inputHandler) {
    inputHandler.getAnyInput("Press enter to return to the main menu: ");
    List<Player> players = gameData.getPlayers();
    appData.getScoreBoard().addEntry(new ScoreBoardEntry(players));
    gameData.reset();
    return ControllerName.MAIN_MENU;
  }
}

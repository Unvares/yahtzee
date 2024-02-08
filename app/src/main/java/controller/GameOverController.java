package controller;

import java.util.List;
import model.AppData;
import model.GameData;
import model.Player;
import model.ScoreBoardEntry;
import utils.ControllerName;
import utils.InputHandler;
import view.GameOverView;

/**
 * Controller for the game over logic.
 */
public class GameOverController extends Controller {
  private AppData appData = AppData.getInstance();
  private GameData gameData = appData.getGameData();

  /**
   * Constructor for GameOverController.
   */
  public GameOverController() {
    super(new GameOverView());
  }

  /**
   * Gets the new controller based on user input.
   *
   * @param inputHandler The input handler for user input.
   * @return The new controller.
   */
  @Override
  protected ControllerName getNewController(InputHandler inputHandler) {
    inputHandler.getAnyInput("Press enter to return to the main menu: ");
    List<Player> players = gameData.getPlayers();
    appData.getScoreBoard().addEntry(new ScoreBoardEntry(players));
    gameData.reset();
    return ControllerName.MAIN_MENU;
  }
}

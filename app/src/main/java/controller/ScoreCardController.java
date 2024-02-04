package controller;

import model.AppData;
import model.GameData;
import model.scorecard.strategies.ScoreCardEntry;
import utils.ControllerName;
import utils.InputHandler;
import view.ScoreCardView;

/**
 * Controller for the ScoreCard.
 */
public class ScoreCardController extends Controller {
  private GameData gameData = AppData.getInstance().getGameData();

  /**
   * Constructor for ScoreCardController.
   */
  public ScoreCardController() {
    super(new ScoreCardView());
  }

  /**
   * Gets the new controller based on user input.
   *
   * @param inputHandler The input handler for user input.
   * @return The new controller.
   */
  @Override
  protected ControllerName getNewController(InputHandler inputHandler) {
    String state = getState();

    if (state == "register") {
      return registerScore(inputHandler);
    } else if (state == "view") {
      inputHandler.getAnyInput("Press enter to return to the game");
      return ControllerName.GAME;
    } else {
      return ControllerName.INVALID;
    }
  }

  /**
   * Registers the score based on user input.
   *
   * @param inputHandler The input handler for user input.
   * @return The new controller.
   */
  private ControllerName registerScore(InputHandler inputHandler) {
    String choice = inputHandler.getStringInput("Enter name of the entry to fill: ").toLowerCase();
    try {
      ScoreCardEntry entry = gameData.getCurrentPlayer().getScoreCard().getScoreCardEntry(choice);
      boolean isYahtzee = choice.equals("yahtzee");
      boolean canScoreYahtzee = isYahtzee && (!entry.isCompleted() || entry.getScore() > 0);
      if ((entry.isCompleted() && !isYahtzee) || canScoreYahtzee) {
        return ControllerName.INVALID;
      }
      entry.setScoreFromDices(gameData.getCurrentDiceValues());
    } catch (Exception e) {
      return ControllerName.INVALID;
    }
    return ControllerName.GAME;
  }

}

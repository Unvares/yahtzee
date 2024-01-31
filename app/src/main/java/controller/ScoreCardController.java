package controller;

import model.AppData;
import model.GameData;
import model.ScoreCard.ScoreCardEntry;
import utils.InputHandler;
import utils.ControllerName;
import view.ScoreCardView;

public class ScoreCardController extends Controller {
  private GameData gameData;

  public ScoreCardController(AppData appData) {
    super(new ScoreCardView(appData.getGameData()));
    this.gameData = appData.getGameData();
  }

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

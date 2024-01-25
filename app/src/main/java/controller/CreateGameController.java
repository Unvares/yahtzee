package controller;

import view.CreateGameView;

import java.util.List;

import model.GameData;
import model.Player;
import utils.InputHandlerImpl;
import utils.State;

public class CreateGameController extends Controller {
  GameData gameData;

  public CreateGameController(GameData gameData) {
    super(new CreateGameView(gameData), State.GAME_CREATE);
    this.gameData = gameData;
  }

  @Override
  protected State getNewState() {
    List<Player> players = gameData.getPlayers();
    int MIN_PLAYERS = gameData.getMinPlayers();
    int MAX_PLAYERS = gameData.getMaxPlayers();

    int choice = InputHandlerImpl.getInstance().getIntInput("Your choice: ");
    switch (choice) {
      case 1:
        if (players.size() < MAX_PLAYERS) {
          String name = InputHandlerImpl.getInstance().getStringInput("Enter name: ");
          boolean isHuman = InputHandlerImpl.getInstance().getBooleanInput("Is human? (y/n): ");

          gameData.addPlayer(new Player(name, isHuman));
        }

        return State.GAME_CREATE;
      case 2:
        if (players.size() > 0) {
          int index = InputHandlerImpl.getInstance().getIntInput("Enter player number: ");

          while (index < 1 || index > players.size()) {
            System.out.println("Invalid player number. Please try again.");
            index = InputHandlerImpl.getInstance().getIntInput("Enter player number: ");
          }

          gameData.removePlayer(index - 1);
        }

        return State.GAME_CREATE;
      case 3:
        if (players.size() < MIN_PLAYERS) {
          return State.GAME_CREATE;
        }

        return State.GAME_PLAY;
      case 4:
        return State.MENU;
      default:
        return State.INVALID;
    }
  }

}

package controller;

import view.CreateGameView;
import view.View;

import java.util.List;

import model.GameData;
import model.Player;
import utils.InputHandlerImpl;
import utils.State;

public class CreateGameController implements Controller {
  private State state = State.GAME_CREATE;
  private View view;
  GameData gameData;

  public CreateGameController(GameData gameData) {
    this.gameData = gameData;
    view = new CreateGameView(gameData);
  }

  @Override
  public State getControllerState() {
    return state;
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

  public State getChoice() {
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

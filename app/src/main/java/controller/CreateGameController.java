package controller;

import view.CreateGameView;
import view.View;
import model.Context;
import model.Players;
import utils.InputHandlerImpl;
import utils.State;

public class CreateGameController implements Controller {
  private State state = State.CREATE_GAME;
  private View view;
  Players players;

  public CreateGameController(Context context) {
    players = context.getPlayers();
    view = new CreateGameView(players);
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
    int choice = InputHandlerImpl.getInstance().getIntInput("Your choice:");
    switch (choice) {
      case 1:
        if (players.getPlayers().size() > 9) {
          return State.CREATE_GAME;
        }

        String name = InputHandlerImpl.getInstance().getStringInput("Enter name: ");
        boolean isHuman = InputHandlerImpl.getInstance().getBooleanInput("Is human? (y/n): ");

        players.addPlayer(name, isHuman);

        return State.CREATE_GAME;
      case 2:
        if (players.getPlayers().size() == 0) {
          return State.CREATE_GAME;
        }

        int index = InputHandlerImpl.getInstance().getIntInput("Enter player number: ");

        while (index < 1 || index > players.getPlayers().size()) {
          System.out.println("Invalid player number. Please try again.");
          index = InputHandlerImpl.getInstance().getIntInput("Enter player number: ");
        }

        players.removePlayer(index - 1);

        return State.CREATE_GAME;
      case 3:
        if (players.getPlayers().size() < 2) {
          return State.CREATE_GAME;
        }

        return State.PLAY;
      case 4:
        return State.MENU;
      default:
        return State.INVALID;
    }
  }

}

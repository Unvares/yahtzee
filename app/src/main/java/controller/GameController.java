package controller;

import model.Context;
import model.Player;
import model.Players;
import utils.InputHandlerImpl;
import utils.State;
import view.GameView;
import view.View;

public class GameController implements Controller {
  private State state = State.PLAY;
  private View view = new GameView();
  private Players players;

  public GameController(Context context) {
    players = context.getPlayers();
  }

  public void run() {

    view.display();

    for (Player player : players.getPlayers()) {
      System.out.println(player.getName());
    }

    State newState = getChoice();

    while (newState == State.INVALID) {
      System.out.println("Invalid choice. Please try again.");
      newState = getChoice();
    }

    Router.pushState(newState);
  }

  public State getControllerState() {
    return this.state;
  }

  public State getChoice() {
    int choice = InputHandlerImpl.getInstance().getIntInput("Your choice:");
    return State.PLAY;
  }

}

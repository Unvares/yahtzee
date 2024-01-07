package controller;

import java.util.Scanner;

public class InputHandlerImpl implements InputHandler {
  private static InputHandler instance = null;
  private Scanner scanner;

  private InputHandlerImpl() {
    this.scanner = new Scanner(System.in);
  }

  public static InputHandler getInstance() {
    if (instance == null) {
      instance = new InputHandlerImpl();
    }
    return instance;
  }

  public void closeScanner() {
    this.scanner.close();
  }

  public int getIntInput() {
    int input = 0;
    boolean valid = false;
    while (!valid) {
      System.out.println("Your choice: ");
      try {
        input = Integer.parseInt(this.scanner.nextLine());
        valid = true;
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please try again.");
      }
    }
    return input;
  }
}

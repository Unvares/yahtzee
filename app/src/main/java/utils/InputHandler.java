package utils;

import java.util.Scanner;

public class InputHandler {
  private static InputHandler instance = null;
  private Scanner scanner;

  private InputHandler() {
    this.scanner = new Scanner(System.in);
  }

  public static InputHandler getInstance() {
    if (instance == null) {
      instance = new InputHandler();
    }
    return instance;
  }

  public void closeScanner() {
    this.scanner.close();
  }

  public int getIntInput(String prompt) {
    int input = 0;
    boolean valid = false;
    while (!valid) {
      System.out.print(prompt);
      try {
        input = Integer.parseInt(this.scanner.nextLine());
        valid = true;
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please try again.");
      }
    }
    return input;
  }

  public String getStringInput(String prompt) {
    String input = "";
    boolean valid = false;
    while (!valid) {
      System.out.print(prompt);
      input = this.scanner.nextLine();
      if (input.length() > 0) {
        valid = true;
      } else {
        System.out.println("Invalid input. Please try again.");
      }
    }
    return input;
  }

  public boolean getBooleanInput(String prompt) {
    boolean input = false;
    boolean valid = false;
    while (!valid) {
      System.out.print(prompt);
      String choice = this.scanner.nextLine();
      if (choice.equals("y")) {
        input = true;
        valid = true;
      } else if (choice.equals("n")) {
        input = false;
        valid = true;
      } else {
        System.out.println("Invalid input. Please try again.");
      }
    }
    return input;
  }

  public String getAnyInput(String prompt) {
    return this.scanner.nextLine();
  }
}

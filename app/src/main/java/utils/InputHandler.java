package utils;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

/**
 * This class is a singleton that handles user input from the console.
 */
public class InputHandler {
  private static InputHandler instance = null;
  private Scanner scanner;

  /**
   * Private constructor to prevent instantiation.
   */
  private InputHandler() {
    this.scanner = new Scanner(System.in, StandardCharsets.UTF_8.name());
  }

  /**
   * Returns the singleton instance of InputHandler.
   *
   * @return the singleton instance of InputHandler.
   */
  public static InputHandler getInstance() {
    if (instance == null) {
      instance = new InputHandler();
    }
    return instance;
  }

  /**
   * Closes the scanner.
   */
  public void closeScanner() {
    this.scanner.close();
  }

  /**
   * Prints the options and gets an integer input from the user.
   *
   * @param optionsList the list of options to print.
   * @param prompt      the prompt to display.
   * @return the integer input from the user.
   */
  public int getIntInput(List<String> optionsList, String prompt) {
    printOptions(optionsList);
    return getIntInput(prompt);
  }

  /**
   * Gets an integer input from the user.
   *
   * @param prompt the prompt to display.
   * @return the integer input from the user.
   */
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

  /**
   * Prints the options and gets a string input from the user.
   *
   * @param optionsList the list of options to print.
   * @param prompt      the prompt to display.
   * @return the string input from the user.
   */
  public String getStringInput(List<String> optionsList, String prompt) {
    printOptions(optionsList);
    return getStringInput(prompt);
  }

  /**
   * Gets a string input from the user.
   *
   * @param prompt the prompt to display.
   * @return the string input from the user.
   */
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

  /**
   * Prints the options and gets a boolean input from the user.
   *
   * @param optionsList the list of options to print.
   * @param prompt      the prompt to display.
   * @return the boolean input from the user.
   */
  public boolean getBooleanInput(List<String> optionsList, String prompt) {
    printOptions(optionsList);
    return getBooleanInput(prompt);
  }

  /**
   * Gets a boolean input from the user.
   *
   * @param prompt the prompt to display.
   * @return the boolean input from the user.
   */
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

  /**
   * Gets any string input from the user.
   *
   * @param prompt the prompt to display.
   * @return the string input from the user.
   */
  public String getAnyInput(String prompt) {
    System.out.print(prompt);
    return this.scanner.nextLine();
  }

  /**
   * Prints the options to the console.
   *
   * @param optionsList the list of options to print.
   */
  private void printOptions(List<String> optionsList) {
    System.out.println("====================================");
    for (String option : optionsList) {
      System.out.println(option);
    }
    System.out.println();
  }
}

package utils;

/**
 * This interface defines methods for handling user input.
 * Classes implementing this interface should follow the Singleton pattern,
 * providing a public static getInstance() method and a private constructor.
 */
public interface InputHandler {

  static InputHandler getInstance() {
    throw new UnsupportedOperationException("This method should be overridden in the implementing singleton class");
  }

  void closeScanner();

  int getIntInput(String prompt);

  String getStringInput(String prompt);

  boolean getBooleanInput(String prompt);

  String getAnyInput(String prompt);
}

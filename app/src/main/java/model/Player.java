package model;

import model.scorecard.ScoreCard;
import utils.DeepCopyUtil;
import utils.Variation;

/**
 * This class represents a player in the Yahtzee game.
 */
public class Player {
  private String name;
  private boolean isHuman;
  private ScoreCard scoreCard;
  private Variation variation = Variation.DEFAULT;

  /**
   * Constructor for Player.
   * Initializes the player with the given name and type (human or computer).
   *
   * @param name    The name of the player.
   * @param isHuman True if the player is a human, false if the player is a
   *                computer.
   */
  public Player(String name, boolean isHuman) {
    this.name = name;
    this.isHuman = isHuman;
    scoreCard = new ScoreCard(variation);
  }

  /**
   * Constructor for Player.
   * Initializes the player with the given name, type (human or computer), and
   * game variation.
   *
   * @param name      The name of the player.
   * @param isHuman   True if the player is a human, false if the player is a
   *                  computer.
   * @param variation The variation of the game.
   */
  public Player(String name, boolean isHuman, Variation variation) {
    this.name = name;
    this.isHuman = isHuman;
    this.variation = variation;
    scoreCard = new ScoreCard(variation);
  }

  /**
   * Returns the name of the player.
   *
   * @return The name of the player.
   */
  public String getName() {
    return name;
  }

  /**
   * Returns whether the player is a human.
   *
   * @return True if the player is a human, false if the player is a computer.
   */
  public boolean isHuman() {
    return isHuman;
  }

  /**
   * Returns the score card of the player.
   *
   * @return The score card of the player.
   */
  public ScoreCard getScoreCard() {
    return DeepCopyUtil.deepCopy(scoreCard);
  }

  /**
   * Sets the score card of the player.
   *
   * @param scoreCard The score card to be set.
   */
  private void setScoreCard(ScoreCard scoreCard) {
    this.scoreCard = scoreCard;
  }

  /**
   * Returns whether the player has filled the score card.
   *
   * @return True if the player has filled the score card, false otherwise.
   */
  public boolean hasFilledScoreCard() {
    return scoreCard.isCompleted();
  }

  /**
   * Converts the player to a CSV string.
   *
   * @return The CSV string representation of the player.
   */
  public String toCsv() {
    StringBuilder csvBuilder = new StringBuilder();
    csvBuilder.append(name + ",");
    csvBuilder.append(isHuman ? "Human" : "Computer").append(":");
    csvBuilder.append(scoreCard.toCsv());
    return csvBuilder.toString();
  }

  /**
   * Creates a Player from a CSV string.
   *
   * @param csv       The CSV string to be converted.
   * @param variation The variation of the game.
   * @return The Player created from the CSV string.
   */
  public static Player fromCsv(String csv, Variation variation) {
    String[] parts = csv.split(":");

    String scoreCardCsv = parts[1];
    ScoreCard scoreCard = ScoreCard.fromCsv(scoreCardCsv, variation);

    String[] playerCsv = parts[0].split(",");
    String name = playerCsv[0];
    boolean isHuman = playerCsv[1].toLowerCase().equals("human");
    Player player = new Player(name, isHuman);
    player.setScoreCard(scoreCard);
    return player;
  }
}

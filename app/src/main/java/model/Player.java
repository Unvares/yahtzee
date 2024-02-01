package model;

import model.ScoreCard.ScoreCard;
import utils.Variation;

public class Player {
  private String name;
  private boolean isHuman;
  private ScoreCard scoreCard;
  private Variation variation = Variation.DEFAULT;

  public Player(String name, boolean isHuman) {
    this.name = name;
    this.isHuman = isHuman;
    scoreCard = new ScoreCard(variation);
  }

  public Player(String name, boolean isHuman, Variation variation) {
    this.name = name;
    this.isHuman = isHuman;
    this.variation = variation;
    scoreCard = new ScoreCard(variation);
  }

  public String getName() {
    return name;
  }

  public boolean isHuman() {
    return isHuman;
  }

  public ScoreCard getScoreCard() {
    return scoreCard;
  }

  private void setScoreCard(ScoreCard scoreCard) {
    this.scoreCard = scoreCard;
  }

  public boolean hasFilledScoreCard() {
    return scoreCard.isCompleted();
  }

  public String toCSV() {
    StringBuilder csvBuilder = new StringBuilder();
    csvBuilder.append(name + ",");
    csvBuilder.append(isHuman ? "Human" : "Computer").append(":");
    csvBuilder.append(scoreCard.toCSV());
    return csvBuilder.toString();
  }

  // Rework it so that it retrieves the info about variation from the CSV file
  public static Player fromCSV(String csv) {
    String[] parts = csv.split(":");

    String scoreCardCSV = parts[1];
    ScoreCard scoreCard = ScoreCard.fromCSV(scoreCardCSV);

    String[] playerCSV = parts[0].split(",");
    String name = playerCSV[0];
    boolean isHuman = playerCSV[1].toLowerCase().equals("human");
    Player player = new Player(name, isHuman);
    player.setScoreCard(scoreCard);
    return player;
  }
}

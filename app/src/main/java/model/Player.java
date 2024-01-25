package model;

public class Player {
  private String name;
  private boolean isHuman;
  private ScoreCard scoreCard;
  private int totalScore;

  public Player(String name, boolean isHuman) {
    this.name = name;
    this.isHuman = isHuman;
    scoreCard = new ScoreCard();
  }

  public Player(String name, boolean isHuman, int score) {
    this.name = name;
    this.isHuman = isHuman;
    totalScore = score;
  }

  public String getName() {
    return name;
  }

  public int getTotalScore() {
    return totalScore;
  }

  public void setTotalScore(int totalScore) {
    this.totalScore = totalScore;
  }

  public boolean isHuman() {
    return isHuman;
  }

  public ScoreCard getScoreCard() {
    return scoreCard;
  }

  public boolean isFinished() {
    return scoreCard.isCompleted();
  }
}

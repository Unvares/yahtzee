package model;

public class Player {
  private String name;
  private boolean isHuman;
  private ScoreCard scoreCard = new ScoreCard();

  public Player(String name, boolean isHuman) {
    this.name = name;
    this.isHuman = isHuman;
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

  public boolean isFinished() {
    return scoreCard.isCompleted();
  }
}

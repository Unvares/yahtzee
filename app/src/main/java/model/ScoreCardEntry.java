package model;

import java.util.List;

public class ScoreCardEntry {
  private String name;
  private boolean isCompleted = false;
  private int score = 0;
  private int yahtzeeCounter = 0;

  public ScoreCardEntry(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public boolean isCompleted() {
    return isCompleted;
  }

  public int getScore() {
    return score;
  }

  public void setScore(String name, List<Integer> diceValues) {
    this.isCompleted = true;
    this.score = calculateScore(name, diceValues);
  }

  public int calculateScore(String name, List<Integer> diceValues) {
    switch (name) {
      case "ones":
        return sumOfValues(diceValues, 1);
      case "twos":
        return sumOfValues(diceValues, 2);
      case "threes":
        return sumOfValues(diceValues, 3);
      case "fours":
        return sumOfValues(diceValues, 4);
      case "fives":
        return sumOfValues(diceValues, 5);
      case "sixes":
        return sumOfValues(diceValues, 6);
      case "three of a kind":
        return NOfAKind(diceValues, 3);
      case "four of a kind":
        return NOfAKind(diceValues, 4);
      case "full house":
        return fullHouse(diceValues);
      case "small straight":
        return straight(diceValues, "small");
      case "large straight":
        return straight(diceValues, "large");
      case "yahtzee":
        return yahtzee(diceValues);
      case "chance":
        return chance(diceValues);
      default:
        return 0;
    }
  }

  private int sumOfValues(List<Integer> diceValues, int value) {
    int sum = 0;
    for (int diceValue : diceValues) {
      if (diceValue == value) {
        sum += diceValue;
      }
    }
    return sum;
  }

  private int sumOfValues(List<Integer> diceValues) {
    int sum = 0;
    for (int diceValue : diceValues) {
      sum += diceValue;
    }
    return sum;
  }

  private int NOfAKind(List<Integer> diceValues, int N) {
    int[] counts = new int[6];
    for (int diceValue : diceValues) {
      counts[diceValue - 1]++;
    }
    for (int i = 0; i < counts.length; i++) {
      if (counts[i] >= N) {
        return sumOfValues(diceValues);
      }
    }
    return 0;
  }

  private int fullHouse(List<Integer> diceValues) {
    int[] counts = new int[6];
    for (int diceValue : diceValues) {
      counts[diceValue - 1]++;
    }
    boolean hasTwo = false;
    boolean hasThree = false;
    for (int i = 0; i < counts.length; i++) {
      if (counts[i] == 2) {
        hasTwo = true;
      }
      if (counts[i] == 3) {
        hasThree = true;
      }
    }
    if (hasTwo && hasThree) {
      return 25;
    }
    return 0;
  }

  private int straight(List<Integer> diceValues, String type) {
    if (!type.equals("small") && !type.equals("large")) {
      throw new IllegalArgumentException("Type must be either 'small' or 'large'");
    }

    int[] counts = new int[6];
    for (int diceValue : diceValues) {
      counts[diceValue - 1]++;
    }
    int count = 0;
    int requiredCount = type.equals("small") ? 4 : 5;
    for (int i = 0; i < counts.length; i++) {
      if (counts[i] > 0) {
        count++;
      } else {
        count = 0;
      }
      if (count == requiredCount) {
        return type.equals("small") ? 30 : 40;
      }
    }
    return 0;
  }

  private int yahtzee(List<Integer> diceValues) {
    int[] counts = new int[6];
    for (int diceValue : diceValues) {
      counts[diceValue - 1]++;
    }
    for (int i = 0; i < counts.length; i++) {
      if (counts[i] == 5) {
        yahtzeeCounter++;

        if (yahtzeeCounter == 1) {
          return 50;
        }
        return 100;
      }
    }
    return 0;
  }

  private int chance(List<Integer> diceValues) {
    return sumOfValues(diceValues);
  }

  public void score(int score) {
    this.isCompleted = true;
    this.score = score;
  }

}

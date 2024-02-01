package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utils.Variation;

public class ScoreBoard {
  private List<ScoreBoardEntry> entryList = new ArrayList<>();
  private File scoreFile;
  private Variation variation = Variation.DEFAULT;

  public ScoreBoard() {
    scoreFile = new File("scores_default.csv");

    loadScoreFile();
  }

  public ScoreBoard(Variation variation) {
    this.variation = variation;

    switch (variation) {
      case MAXI:
        scoreFile = new File("scores_maxi.csv");
        break;
      default:
        scoreFile = new File("scores_default.csv");
        break;
    }

    loadScoreFile();
  }

  private void loadScoreFile() {
    if (!scoreFile.exists()) {
      try {
        scoreFile.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    loadScoreBoard();
  }

  private void loadScoreBoard() {
    try (BufferedReader reader = new BufferedReader(new FileReader(scoreFile))) {
      String line;
      while ((line = reader.readLine()) != null) {
        ScoreBoardEntry entry = ScoreBoardEntry.fromCSV(line, variation);
        entryList.add(entry);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void addEntry(ScoreBoardEntry entry) {
    entryList.add(entry);
    try (FileWriter writer = new FileWriter(scoreFile, true)) {
      writer.write(entry.toCSV() + "\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public List<ScoreBoardEntry> getEntries() {
    return entryList;
  }

}

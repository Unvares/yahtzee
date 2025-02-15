package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import utils.DeepCopyUtil;
import utils.Variation;

/**
 * This class represents the scoreboard of the Yahtzee game.
 */
public class ScoreBoard implements Serializable {
  private List<ScoreBoardEntry> entryList = new ArrayList<>();
  private File scoreFile;
  private Variation variation = Variation.DEFAULT;

  /**
   * Default constructor for ScoreBoard.
   * Initializes the score file with the default variation.
   */
  public ScoreBoard() {
    scoreFile = new File("scores_default.csv");

    loadScoreFile();
  }

  /**
   * Constructor for ScoreBoard.
   * Initializes the score file with the given variation.
   *
   * @param variation The variation of the game.
   */
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

  /**
   * Loads the score file.
   * If the file does not exist, it creates a new one.
   */
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

  /**
   * Loads the scoreboard from the score file.
   */
  private void loadScoreBoard() {
    try (BufferedReader reader = new BufferedReader(
        new InputStreamReader(new FileInputStream(scoreFile), StandardCharsets.UTF_8))) {
      String line;
      while ((line = reader.readLine()) != null) {
        ScoreBoardEntry entry = ScoreBoardEntry.fromCsv(line, variation);
        entryList.add(entry);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Adds an entry to the scoreboard and writes it to the score file.
   *
   * @param entry The entry to be added.
   */
  public void addEntry(ScoreBoardEntry entry) {
    entryList.add(entry);
    try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(scoreFile, true),
        StandardCharsets.UTF_8)) {
      writer.write(entry.toCsv() + "\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Returns the list of entries in the scoreboard.
   *
   * @return The list of entries.
   */
  public List<ScoreBoardEntry> getEntries() {
    List<ScoreBoardEntry> copy = new ArrayList<>();
    for (ScoreBoardEntry entry : entryList) {
      copy.add(DeepCopyUtil.deepCopy(entry));
    }
    return copy;
  }

}

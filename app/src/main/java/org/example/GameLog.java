package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class GameLog {
  private int xWins = 0;
  private int oWins = 0;
  private int ties  = 0;

  public void record(Result result) {
    switch (result) {
      case X_WINS -> xWins++;
      case O_WINS -> oWins++;
      case DRAW   -> ties++;
      default     -> {}
    }
  }

  public String render() {
    return new StringBuilder()
        .append(String.format("Player X Wins   %d%n", xWins))
        .append(String.format("Player O Wins   %d%n", oWins))
        .append(String.format("Ties            %d", ties))
        .toString();
  }

  public void saveToFile(String filename) {
    String header = "Final Tic-Tac-Toe Game Log\n\n";
    String content = header + render() + "\n";
    try {
      Files.writeString(Path.of(filename), content);
    } catch (IOException e) {
      System.out.println("\nWarning: failed to write game log to disk: " + e.getMessage());
    }
  }
}

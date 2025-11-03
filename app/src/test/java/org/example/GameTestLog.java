package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameLogTest {
  @Test
  void recordsAndRenders() {
    GameLog log = new GameLog();
    log.record(Result.X_WINS);
    log.record(Result.O_WINS);
    log.record(Result.DRAW);
    String out = log.render();
    assertTrue(out.contains("Player X Wins   1"));
    assertTrue(out.contains("Player O Wins   1"));
    assertTrue(out.contains("Ties            1"));
  }
}
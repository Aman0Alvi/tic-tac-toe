package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {

  @Test
  void xStartsAndSwapsTurns() {
    Game g = new Game(new Board());
    assertEquals('X', g.getCurrentPlayer());
    assertTrue(g.tryMove(0));
    assertEquals('O', g.getCurrentPlayer());
    assertTrue(g.tryMove(4));
    assertEquals(Result.IN_PROGRESS, g.getStatus());
  }

  @Test
  void detectsWin() {
    Game g = new Game(new Board());
    g.tryMove(0); // X
    g.tryMove(3); // O
    g.tryMove(1); // X
    g.tryMove(4); // O
    g.tryMove(2); // X wins
    assertEquals(Result.X_WINS, g.getStatus());
  }

  @Test
  void rejectsInvalidIndexAndTakenCell() {
    Game g = new Game(new Board());
    assertFalse(g.tryMove(99)); // out of range
    assertTrue(g.tryMove(0)); // X
    assertFalse(g.tryMove(0)); // O attempts taken cell
  }
}

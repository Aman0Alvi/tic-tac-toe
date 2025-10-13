package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

  @Test
  void emptyBoardNotFullNoWinner() {
    Board b = new Board();
    assertFalse(b.isFull());
    assertFalse(b.hasWinner());
  }

  @Test
  void placementAndWinner() {
    Board b = new Board();
    assertTrue(b.place(0, 'X'));
    assertTrue(b.place(1, 'X'));
    assertTrue(b.place(2, 'X'));
    assertTrue(b.hasWinner());
  }

  @Test
  void cannotPlaceOnTakenCell() {
    Board b = new Board();
    assertTrue(b.place(4, 'O'));
    assertFalse(b.place(4, 'X'));
  }
}

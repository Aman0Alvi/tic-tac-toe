package org.example;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class OpportunisticComputerMoveProvider implements MoveProvider {
  private final Random rng = new Random();

  @Override
  public int getMove(Board board, char me, char opp, Scanner sc, PrintStream out) {
    if (board.movesPlayed() == 0) {
      int[] corners = {0,2,6,8};
      List<Integer> avail = new ArrayList<>();
      for (int c : corners) if (board.isEmpty(c)) avail.add(c);
      if (!avail.isEmpty()) return avail.get(rng.nextInt(avail.size()));
    }

    if (board.movesPlayed() == 1 && board.isEmpty(4)) {
      return 4;
    }

    for (int idx : board.emptyIndices()) {
      if (board.wouldWinIfPlaced(idx, me)) return idx;
    }

    for (int idx : board.emptyIndices()) {
      if (board.wouldWinIfPlaced(idx, opp)) return idx;
    }

    List<Integer> empties = board.emptyIndices();
    return empties.get(rng.nextInt(empties.size()));
  }
}

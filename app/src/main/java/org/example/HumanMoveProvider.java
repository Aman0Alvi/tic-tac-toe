package org.example;

import java.io.PrintStream;
import java.util.Scanner;

public class HumanMoveProvider implements MoveProvider {
  @Override
  public int getMove(Board board, char player, char opponent, Scanner sc, PrintStream out) {
    while (true) {
      out.print("\nWhat is your move?  ");
      String line = sc.nextLine().trim();

      if (!line.matches("[1-9]")) {
        out.println("\nThat is not a valid move! Try again.");
        out.println();
        out.println(board.render());
        continue;
      }
      int idx = Integer.parseInt(line) - 1;
      if (!board.isEmpty(idx)) {
        out.println("\nThat is not a valid move! Try again.");
        out.println();
        out.println(board.render());
        continue;
      }
      return idx;
    }
  }
}

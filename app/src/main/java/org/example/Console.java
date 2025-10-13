package org.example;

import java.io.PrintStream;
import java.util.Scanner;

public class Console {
  private final PrintStream out;

  public Console(PrintStream out) { this.out = out; }

  public void playOneGame(Game game, Scanner sc) {
    out.println(game.getBoard().render());
    while (game.getStatus() == Result.IN_PROGRESS) {
      int idx = promptForMove(sc, game.getCurrentPlayer(), game.getBoard());
      game.tryMove(idx);
      out.println();
      out.println(game.getBoard().render());
    }
    announce(game.getStatus());
  }

  private int promptForMove(Scanner sc, char player, Board board) {
    while (true) {
      out.print("\nWhat is your move?  ");
      String line = sc.nextLine().trim();

      if (!line.matches("[1-9]")) {
        invalidMove(board);
        continue;
      }
      int idx = Integer.parseInt(line) - 1;
      if (!board.isEmpty(idx)) {
        invalidMove(board);
        continue;
      }
      return idx;
    }
  }

  private void invalidMove(Board board) {
    out.println("\nThat is not a valid move! Try again.");
    out.println();
    out.println(board.render());
  }

  private void announce(Result r) {
    switch (r) {
      case X_WINS -> out.println("\nPlayer X wins!");
      case O_WINS -> out.println("\nPlayer O wins!");
      case DRAW -> out.println("\nIt's a draw!");
      default -> {}
    }
  }
}

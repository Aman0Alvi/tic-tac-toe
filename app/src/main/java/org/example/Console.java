package org.example;

import java.io.PrintStream;
import java.util.Scanner;

public class Console {
  private final PrintStream out;
  private final MoveProvider moveX;
  private final MoveProvider moveO;

  public Console(PrintStream out, MoveProvider moveX, MoveProvider moveO) {
    this.out = out;
    this.moveX = moveX;
    this.moveO = moveO;
  }

  public void playOneGame(Game game, Scanner sc) {
    out.println(game.getBoard().render());
    while (game.getStatus() == Result.IN_PROGRESS) {
      char player = game.getCurrentPlayer();
      MoveProvider provider = (player == 'X') ? moveX : moveO;

      int idx = provider.getMove(
          game.getBoard(),
          player,
          (player == 'X') ? 'O' : 'X',
          sc,
          out
      );

      game.tryMove(idx);
      out.println();
      out.println(game.getBoard().render());
    }
    announce(game.getStatus());
  }

  private void announce(Result r) {
    switch (r) {
      case X_WINS -> out.println("\nPlayer X wins!");
      case O_WINS -> out.println("\nPlayer O wins!");
      case DRAW   -> out.println("\nIt's a draw!");
      default     -> {}
    }
  }
}

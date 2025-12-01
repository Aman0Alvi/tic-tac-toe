package org.example;

import java.io.PrintStream;
import java.util.Scanner;

public interface MoveProvider {
  int getMove(Board board, char player, char opponent, Scanner sc, PrintStream out);
}

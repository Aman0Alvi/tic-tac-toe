package org.example;

import java.util.Scanner;

public class App {
  public String getGreeting() {
    return "Welcome to Tic-Tac-Toe!";
  }

  private final char[] board = new char[9];
  private char currentPlayer = 'X';

  public static void main(String[] args) {
    App game = new App();
    game.run();
  }

  private void run() {
    try (Scanner sc = new Scanner(System.in)) {
      System.out.println("Welcome to Tic-Tac-Toe!\n");
      boolean playAgain = true;
      while (playAgain) {
        startNewGame();
        playOneGame(sc);

        while (true) {
          System.out.print("\nWould you like to play again (yes/no)? ");
          String line = sc.nextLine().trim();
          if (line.equalsIgnoreCase("yes")) {
            System.out.println();
            playAgain = true;
            break;
          } else if (line.equalsIgnoreCase("no")) {
            System.out.println("\nGoodbye!");
            playAgain = false;
            break;
          } else {
            System.out.println("\nThat is not a valid entry!");
          }
        }
      }
    }
  }

  private void startNewGame() {
    for (int i = 0; i < 9; i++) board[i] = ' ';
    currentPlayer = 'X';
    printBoard();
  }

  private void playOneGame(Scanner sc) {
    while (true) {
      int move = promptForMove(sc);
      board[move] = currentPlayer;

      System.out.println();
      printBoard();

      if (hasWinner()) {
        System.out.println("\nPlayer " + currentPlayer + " wins!");
        return;
      }
      if (isDraw()) {
        System.out.println("\nIt's a draw!");
        return;
      }

      currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }
  }

  private int promptForMove(Scanner sc) {
    while (true) {
      System.out.print("\nWhat is your move?  ");
      String line = sc.nextLine().trim();

      if (!line.matches("[1-9]")) {
        System.out.println("\nThat is not a valid move! Try again.");
        System.out.println();
        printBoard();
        continue;
      }

      int idx = Integer.parseInt(line) - 1;
      if (board[idx] != ' ') {
        System.out.println("\nThat is not a valid move! Try again.");
        System.out.println();
        printBoard();
        continue;
      }

      return idx;
    }
  }

  private void printBoard() {
    String[] cells = new String[9];
    for (int i = 0; i < 9; i++) {
      cells[i] = (board[i] == ' ') ? String.valueOf(i + 1) : String.valueOf(board[i]);
    }

    System.out.println(
        "    " + cells[0] + "  |  " + cells[1] + "  |  " + cells[2] + "\n" +
        "  -----+-----+-----\n" +
        "    " + cells[3] + "  |  " + cells[4] + "  |  " + cells[5] + "\n" +
        "  -----+-----+-----\n" +
        "    " + cells[6] + "  |  " + cells[7] + "  |  " + cells[8]
    );
  }

  private boolean hasWinner() {
    int[][] lines = {
        {0,1,2},{3,4,5},{6,7,8}, // rows
        {0,3,6},{1,4,7},{2,5,8}, // cols
        {0,4,8},{2,4,6} // diagonals
    };
    for (int[] L : lines) {
      if (board[L[0]] != ' ' &&
          board[L[0]] == board[L[1]] &&
          board[L[1]] == board[L[2]]) {
        return true;
      }
    }
    return false;
  }

  private boolean isDraw() {
    for (char c : board) if (c == ' ') return false;
    return true;
  }
}

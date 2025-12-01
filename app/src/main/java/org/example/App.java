package org.example;

import java.util.Scanner;

public class App {
  public String getGreeting() {
    return "Welcome to Tic-Tac-Toe!";
  }

  private static int readMenuSelection(Scanner sc) {
    System.out.println("What kind of game would you like to play?\n");
    System.out.println("1. Human vs. Human");
    System.out.println("2. Human vs. Computer");
    System.out.println("3. Computer vs. Human\n");
    while (true) {
      System.out.print("What is your selection? ");
      String line = sc.nextLine().trim();
      if (line.matches("[1-3]")) return Integer.parseInt(line);
      System.out.println("That is not a valid entry!");
    }
  }

  public static void main(String[] args) {
    System.out.println("Welcome to Tic-Tac-Toe!\n");

    GameLog log = new GameLog();
    char nextStarter = 'X';

    try (Scanner sc = new Scanner(System.in)) {
      int selection = readMenuSelection(sc);
      MoveProvider human = new HumanMoveProvider();
      MoveProvider ai    = new OpportunisticComputerMoveProvider();

      MoveProvider moveX;
      MoveProvider moveO;

      switch (selection) {
        case 1 -> {
          moveX = human; moveO = human;
          System.out.println("\nGreat! Human vs. Human.\n");
        }
        case 2 -> {
          moveX = human; moveO = ai;
          nextStarter = 'X'; // human starts
          System.out.println("\nGreat! Human will go first.\n");
        }
        case 3 -> {
          moveX = ai; moveO = human;
          nextStarter = 'X'; // computer starts as X
          System.out.println("\nGreat! The computer will go first.\n");
        }
        default -> {
          moveX = human; moveO = human;
        }
      }

      Console ui = new Console(System.out, moveX, moveO);

      boolean again = true;
      while (again) {
        Game game = new Game(new Board(), nextStarter);
        ui.playOneGame(game, sc);

        Result r = game.getStatus();
        log.record(r);

        System.out.println("\nThe current log is:\n");
        System.out.println(log.render());

        if (r == Result.X_WINS) {
          nextStarter = 'O';
        } else if (r == Result.O_WINS) {
          nextStarter = 'X';
        }

        while (true) {
          System.out.print("\nWould you like to play again (yes/no)? ");
          String line = sc.nextLine().trim();
          if (line.equalsIgnoreCase("yes")) {
            System.out.println("\nGreat! This time " + nextStarter + " will go first!\n");
            break;
          } else if (line.equalsIgnoreCase("no")) {
            System.out.println("\nWriting the game log to disk. Please see game.txt for the final statistics!");
            log.saveToFile("game.txt");
            System.out.println("\nGoodbye!");
            again = false;
            break;
          } else {
            System.out.println("\nThat is not a valid entry!");
          }
        }
      }
    }
  }
}

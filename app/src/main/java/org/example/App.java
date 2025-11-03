package org.example;

import java.util.Scanner;

public class App {
  public String getGreeting() {
    return "Welcome to Tic-Tac-Toe!";
  }

  public static void main(String[] args) {
    System.out.println("Welcome to Tic-Tac-Toe!\n");

    GameLog log = new GameLog();
    char nextStarter = 'X';

    try (Scanner sc = new Scanner(System.in)) {
      Console ui = new Console(System.out);

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

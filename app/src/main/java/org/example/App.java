package org.example;

import java.util.Scanner;

public class App {
  public String getGreeting() {
    return "Welcome to Tic-Tac-Toe!";
  }

  public static void main(String[] args) {
    System.out.println("Welcome to Tic-Tac-Toe!\n");

    try (Scanner sc = new Scanner(System.in)) {
      Console ui = new Console(System.out);

      boolean again = true;
      while (again) {
        Game game = new Game(new Board());
        ui.playOneGame(game, sc);

        while (true) {
          System.out.print("\nWould you like to play again (yes/no)? ");
          String line = sc.nextLine().trim();
          if (line.equalsIgnoreCase("yes")) {
            System.out.println();
            break;
          } else if (line.equalsIgnoreCase("no")) {
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

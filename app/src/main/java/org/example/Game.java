package org.example;

public class Game {
  private final Board board;
  private char current = 'X';
  private Result status = Result.IN_PROGRESS;

  public Game(Board board) { this.board = board; }
  public Game(Board board, char starting) {
    this.board = board;
    this.current = starting;
  }

  public char getCurrentPlayer() { return current; }
  public Result getStatus() { return status; }
  public Board getBoard() { return board; }

  public boolean tryMove(int zeroBasedIndex) {
    if (status != Result.IN_PROGRESS) return false;

    boolean placed;
    try { placed = board.place(zeroBasedIndex, current); }
    catch (IndexOutOfBoundsException e) { return false; }
    if (!placed) return false;

    if (board.hasWinner()) {
      status = (current == 'X') ? Result.X_WINS : Result.O_WINS;
    } else if (board.isFull()) {
      status = Result.DRAW;
    } else {
      current = (current == 'X') ? 'O' : 'X';
    }
    return true;
  }

  public void reset() {
    board.clear();
    current = 'X';
    status = Result.IN_PROGRESS;
  }
}

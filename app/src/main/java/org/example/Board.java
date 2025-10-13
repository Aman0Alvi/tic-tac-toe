package org.example;

import java.util.Arrays;

public class Board {
  private final char[] cells = new char[9]; 

  public Board() { clear(); }

  public void clear() { Arrays.fill(cells, ' '); }

  public boolean isEmpty(int idx) { checkIndex(idx); return cells[idx] == ' '; }

  public boolean place(int idx, char player) {
    checkIndex(idx);
    if (cells[idx] != ' ') return false;
    cells[idx] = player;
    return true;
  }

  public boolean hasWinner() {
    int[][] lines = {
        {0,1,2},{3,4,5},{6,7,8},
        {0,3,6},{1,4,7},{2,5,8},
        {0,4,8},{2,4,6}
    };
    for (int[] L : lines) {
      char a = cells[L[0]], b = cells[L[1]], c = cells[L[2]];
      if (a != ' ' && a == b && b == c) return true;
    }
    return false;
  }

  public boolean isFull() {
    for (char c : cells) if (c == ' ') return false;
    return true;
  }

  public String render() {
    String[] s = new String[9];
    for (int i = 0; i < 9; i++) s[i] = (cells[i] == ' ') ? String.valueOf(i+1) : String.valueOf(cells[i]);
    return
        "    " + s[0] + "  |  " + s[1] + "  |  " + s[2] + "\n" +
        "  -----+-----+-----\n" +
        "    " + s[3] + "  |  " + s[4] + "  |  " + s[5] + "\n" +
        "  -----+-----+-----\n" +
        "    " + s[6] + "  |  " + s[7] + "  |  " + s[8];
  }

  private void checkIndex(int idx) {
    if (idx < 0 || idx >= 9) throw new IndexOutOfBoundsException("cell " + idx);
  }
}

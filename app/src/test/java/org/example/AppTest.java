package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

class AppTest {
  @Test
  void appHasAGreeting() {
    App classUnderTest = new App();
    assertNotNull(classUnderTest.getGreeting(), "app should have a greeting");
  }

  private static void setBoard(App app, String nineChars) throws Exception {
    if (nineChars.length() != 9) throw new IllegalArgumentException("need exactly 9 chars");
    Field f = App.class.getDeclaredField("board");
    f.setAccessible(true);
    char[] board = (char[]) f.get(app); 
    for (int i = 0; i < 9; i++) board[i] = nineChars.charAt(i);
  }

  private static boolean callHasWinner(App app) throws Exception {
    Method m = App.class.getDeclaredMethod("hasWinner");
    m.setAccessible(true);
    return (boolean) m.invoke(app);
  }

  private static boolean callIsDraw(App app) throws Exception {
    Method m = App.class.getDeclaredMethod("isDraw");
    m.setAccessible(true);
    return (boolean) m.invoke(app);
  }

  private static int callPromptForMove(App app, String fakeInput) throws Exception {
    Method m = App.class.getDeclaredMethod("promptForMove", Scanner.class);
    m.setAccessible(true);
    ByteArrayInputStream in = new ByteArrayInputStream(fakeInput.getBytes(StandardCharsets.UTF_8));
    try (Scanner sc = new Scanner(in)) {
      return (int) m.invoke(app, sc);
    }
  }

  @Test
  void noWinnerAtStartAndNotDraw() throws Exception {
    App app = new App();
    setBoard(app, "         ");
    assertFalse(callHasWinner(app));
    assertFalse(callIsDraw(app));
  }

  @Test
  void detectsRowWin() throws Exception {
    App app = new App();
    setBoard(app, "XXX      ");
    assertTrue(callHasWinner(app));
  }

  @Test
  void detectsDiagonalWin() throws Exception {
    App app = new App();
    setBoard(app, "  O O O  ");
    assertTrue(callHasWinner(app));
  }

  @Test
  void detectsDrawWithoutWinner() throws Exception {
    App app = new App();
    setBoard(app, "XOXOOXXXO");
    assertFalse(callHasWinner(app));
    assertTrue(callIsDraw(app));
  }

  @Test
  void promptSkipsInvalidSymbolThenAcceptsValidMove() throws Exception {
    App app = new App();
    setBoard(app, "         ");
    int idx = callPromptForMove(app, "$\n7\n"); 
    assertEquals(6, idx); 
  }

  @Test
  void promptRejectsMultiDigitThenAcceptsValidMove() throws Exception {
    App app = new App();
    setBoard(app, "         ");
    int idx = callPromptForMove(app, "258\n3\n"); 
    assertEquals(2, idx);
  }

  @Test
  void promptRejectsTakenCellThenAcceptsAnother() throws Exception {
    App app = new App();
    setBoard(app, "   X     "); 
    int idx = callPromptForMove(app, "4\n6\n");
    assertEquals(5, idx);
  }
}

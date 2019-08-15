package edu.bu.met.cs665;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {

  @Test
  public void test() {

    Board board = new Board();

    // verify empty locations have been placed throughout the 2d array
    for (int row = 0; row < board.boardGrid.length; row++) {
      for (int col = 0; col < board.boardGrid[row].length; col++) {
        assert (board.boardGrid[row][col].checkEmpty());
      }
    }

    // verify the check adjacent method works - positive case
    int xy[] = { 1, 1 };
    int previousLocation[] = { 1, 2 };
    assert (board.checkAdjacent(xy, previousLocation));

    // negative case
    int previousLocation2[] = { 2, 2 };

    assertFalse(board.checkAdjacent(xy, previousLocation2));
  }

}

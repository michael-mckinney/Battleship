package edu.bu.met.cs665;

public class Board {
  
  ControlTower[][] boardGrid = new ControlTower[5][5];
  
  //do this in the constructor instead I think
  public void initializeBoard() {
    //iterate through boardGrid and add Locations
    for (int row = 0; row < boardGrid.length; row ++) {
      for (int col = 0; col < boardGrid[row].length; col++) {
        boardGrid[row][col] = new ControlTower();
      }
    }
    
  }

}

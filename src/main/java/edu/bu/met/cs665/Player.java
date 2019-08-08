package edu.bu.met.cs665;

public class Player {
  
  private String playerName;
  private Board board;
  
  
  public Player(String name) {
    this.playerName = name;  
  }
  
  public String getPlayerName() {
    return this.playerName;
  }
  
  public Board createBoard() {
    board = new Board();
    return board;
  }
  
}

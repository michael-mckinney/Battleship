package edu.bu.met.cs665;

public class Player {

  private String playerName;
  private Board board;

  // create a board for each player
  public Player(String name) {
    this.playerName = name;
    this.board = new Board();
  }

  public String getPlayerName() {
    return this.playerName;
  }

  public Board getPlayerBoard() {
    return this.board;
  }

}

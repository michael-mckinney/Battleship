package edu.bu.met.cs665;

import java.util.Observable;

public abstract class Ship extends Observable {

  protected String shipName;
  protected int shipSize;
  protected int shipHealth;

  // get methods
  protected String getShipName() {
    return this.shipName;
  }

  protected int getShipSize() {
    return this.shipSize;
  }
  
  protected int getShipHealth() {
    return this.shipHealth;
  }

  public void decrementHealth() {
    --this.shipHealth;
    // check if ship is destroyed and notify if it is
    if (shipHealth == 0) {
      setChanged();
      notifyObservers(this);
    }
  }

  public void addBoardObserver(Board board) {
    this.addObserver(board);
  }

}

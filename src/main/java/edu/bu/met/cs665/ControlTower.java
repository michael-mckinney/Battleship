package edu.bu.met.cs665;

public class ControlTower {
  
  //initialize a Battleship to null
  private Ship ship = null;
  private Location location;
  
  public ControlTower() {
    this.location = new Location();
  }
  
  public void addShip(Ship ship) {
    this.ship = ship;
    this.location.set_state(new OccupiedLocation());
  }
  
  //need a method for recording a hit
  // the ship should decrement its life
  // if it is destroyed it should notify through the observer pattern that shipsRemaining should be decremented
  // so Battleship should probably be the one that keeps track of that

}

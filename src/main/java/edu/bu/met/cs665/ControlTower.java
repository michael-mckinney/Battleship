package edu.bu.met.cs665;

public class ControlTower {
  
  //initialize a ship to null
  private Ship ship = null;
  private Location location;
  
  public ControlTower() {
    this.location = new Location();
  }
  
  public void addShip(Ship ship) {
    this.ship = ship;
    this.location.set_state(new OccupiedLocation());
  }
  
  public Location getLocation() {
	  return this.location;
  }
  
  public Ship getShip() {
	  return this.ship;
  }


}

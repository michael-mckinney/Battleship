package edu.bu.met.cs665;

public class ControlTower {

  // initialize a ship to null
  private Ship ship = null;
  private Location location;

  public ControlTower() {
    this.location = new Location();
  }

  // add a ship and set location to occupied state
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

  // various checks on the location
  public boolean checkOccupied() {
    if (this.location.getStatus().contentEquals("Occupied")) {
      return true;
    } else {
      return false;
    }
  }

  public boolean checkEmpty() {
    if (this.location.getStatus().contentEquals("Empty")) {
      return true;
    } else {
      return false;
    }
  }

  public boolean checkHit() {
    if (this.location.getStatus().contentEquals("Hit")) {
      return true;
    } else {
      return false;
    }
  }

  public boolean checkMissed() {
    if (this.location.getStatus().contentEquals("Missed")) {
      return true;
    } else {
      return false;
    }
  }
  
  // update methods

  public void setLocationState(LocationState state) {
    this.location.set_state(state);
  }
  
  public void setHit() {
    setLocationState(new HitLocation());
    this.ship.decrementHealth();
  }
  
  public void setMissed() {
    setLocationState(new MissedLocation());
  }

}

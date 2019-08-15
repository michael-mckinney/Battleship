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
  
  public boolean checkOccupied(int[] xy) {
		if (this.location.getStatus().contentEquals("Occupied")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkEmpty(int[] xy) {
		if (this.location.getStatus().contentEquals("Empty")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkHit(int[] xy) {
		if (this.location.getStatus().contentEquals("Hit")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkMissed(int[] xy) {
		if (this.location.getStatus().contentEquals("Missed")) {
			return true;
		} else {
			return false;
		}
	}
	
	public void setLocationState(LocationState state) {
		this.location.set_state(state);
	}


}

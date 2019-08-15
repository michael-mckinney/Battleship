package edu.bu.met.cs665;

public class Location {

  private LocationState currentState;

  public Location() {
    // initially set Location to an Empty State
    currentState = new EmptyLocation();
  }

  public void set_state(LocationState state) {
    currentState = state;
  }

  public String getStatus() {
    return currentState.getStatus();
  }

  public String getDisplay() {
    return currentState.getDisplay();
  }

}

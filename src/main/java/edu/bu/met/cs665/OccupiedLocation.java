package edu.bu.met.cs665;

public class OccupiedLocation implements LocationState {

  private static String status = "Occupied";
  private static String display = "-";

  public String getStatus() {
    return status;
  }

  public String getDisplay() {
    return display;
  }

}

package edu.bu.met.cs665;

public class MissedLocation implements LocationState {
  private static String status = "Missed";
  private static String display = "*";

  public String getStatus() {
    return status;
  }

  public String getDisplay() {
    return display;
  }

}

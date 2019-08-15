package edu.bu.met.cs665;

public class EmptyLocation implements LocationState {

  private static String status = "Empty";
  private static String display = "-";

  public String getStatus() {
    return status;
  }

  public String getDisplay() {
    return display;
  }

}

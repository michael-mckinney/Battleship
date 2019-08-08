package edu.bu.met.cs665;

public class HitLocation implements LocationState{
  
  private static String status = "Hit";
  private static String display = "X";

  public String getStatus() {
    return status;
  }
  
  public String getDisplay () {
    return display;
  }
  
  // throw an exception as no shots should be allowed on already hit locations
  public void recordShot() {
    try {
      throw new Exception();
    }
    catch (Exception e) {
      System.out.println("No shots should be allowed on already hit locations");
    }
  }

}

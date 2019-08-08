package edu.bu.met.cs665;

public class MissedLocation implements LocationState{
  private static String status = "Missed";
  private static String display = "*";

  public String getStatus() {
    return status;
  }
  
  public String getDisplay () {
    return display;
  }
  
  // throw an exception as no shots should be allowed on missed locations
  public void recordShot() {
    try {
      throw new Exception();
    }
    catch (Exception e) {
      System.out.println("No shots should be allowed on missed locations");
    }
  }
  
  
  
}

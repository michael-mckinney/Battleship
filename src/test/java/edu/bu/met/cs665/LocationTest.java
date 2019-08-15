package edu.bu.met.cs665;

import static org.junit.Assert.*;

import org.junit.Test;

public class LocationTest {

  @Test
  public void test() {

    Location location = new Location();

    // verify it is initially set to an Empty State
    assertEquals("Empty", location.getStatus());

    // verify initial display
    assertEquals("-", location.getDisplay());

    // set it to a Occupied State
    location.set_state(new OccupiedLocation());

    // verify new state
    // verify it is initially set to an Empty State
    assertEquals("Occupied", location.getStatus());

    // verify display
    assertEquals("-", location.getDisplay());

    // set it to a Missed State
    location.set_state(new MissedLocation());

    // verify new state
    // verify it is initially set to an Empty State
    assertEquals("Missed", location.getStatus());

    // verify display
    assertEquals("*", location.getDisplay());

    // set it to a Occupied State
    location.set_state(new HitLocation());

    // verify new state
    // verify it is initially set to an Empty State
    assertEquals("Hit", location.getStatus());

    // verify display
    assertEquals("X", location.getDisplay());

  }

}

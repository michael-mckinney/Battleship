package edu.bu.met.cs665;

import static org.junit.Assert.*;

import org.junit.Test;

public class ControlTowerTest {

  @Test
  public void test() {

    ControlTower ct = new ControlTower();
    Player player = new Player("Steve");

    Ship ship = ShipFactory.getShip("Destroyer", player);

    // add a ship to the control tower
    ct.addShip(ship);

    // verify ship added
    assertEquals("Destroyer", ct.getShip().getShipName());

    // verify location is now occupied
    assert (ct.checkOccupied());

    // set the state to Hit
    ct.setLocationState(new HitLocation());

    assert (ct.checkHit());

  }

}

package edu.bu.met.cs665;

import static org.junit.Assert.*;

import org.junit.Test;

public class ShipTest {

  @Test
  public void test() {

    // create a Player - Board is automatically created as well
    Player player = new Player("Michael");

    // set ships Remaining to 1
    player.getPlayerBoard().setShipsRemaining(1);

    Ship ship = ShipFactory.getShip("Submarine", player);

    assertEquals("Submarine", ship.getShipName());
    assertEquals(3, ship.getShipSize());

    ship.decrementHealth();

    assertEquals(2, ship.getShipHealth());

    // decrement health until ship is destroyed
    ship.decrementHealth();
    ship.decrementHealth();

    // verify that the board ships remaining count decremented
    assertEquals(0, player.getPlayerBoard().shipsRemaining);

  }

}

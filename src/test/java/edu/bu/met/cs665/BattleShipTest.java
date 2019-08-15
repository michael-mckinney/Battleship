package edu.bu.met.cs665;

import org.junit.Test;

public class BattleShipTest {

  @Test
  public void test() {

    BattleShip bs = new BattleShip();
    Player player = new Player("Mike");

    // dummy set up of board with only a destroyer
    Ship ship = ShipFactory.getShip("Destroyer", player);

    // set up ships remaining to just 1
    player.getPlayerBoard().setShipsRemaining(1);

    // place ship in upper left corner
    player.getPlayerBoard().boardGrid[1][1].addShip(ship);
    player.getPlayerBoard().boardGrid[1][2].addShip(ship);

    // test register Shot

    // shot will be at 1,1
    int shot[] = { 1, 1 };

    bs.registerShot(player, shot);

    // verify that the location is now a Hit location
    assert (player.getPlayerBoard().boardGrid[shot[0]][shot[1]].checkHit());

    // next shot will be at 1,1
    int shot2[] = { 1, 2 };

    bs.registerShot(player, shot2);

    // verify that the location is now a Hit location
    assert (player.getPlayerBoard().boardGrid[shot2[0]][shot2[1]].checkHit());

    // verify game over
    assert (bs.gameOver(player));
  }

}

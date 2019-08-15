package edu.bu.met.cs665;

public class ShipFactory {

  public static Ship getShip(String type, Player player) {
    Ship ship;

    // ship should be created and have the board added as an observer
    switch (type) {
      case "Destroyer":
        ship = new ShipDestroyer();
        ship.addBoardObserver(player.getPlayerBoard());
        break;
      case "Submarine":
        ship = new ShipSubmarine();
        ship.addBoardObserver(player.getPlayerBoard());
        break;
      case "Cruiser":
        ship = new ShipCruiser();
        ship.addBoardObserver(player.getPlayerBoard());
        break;
      case "Battleship":
        ship = new ShipBattleShip();
        ship.addBoardObserver(player.getPlayerBoard());
        break;
      case "Carrier":
        ship = new ShipCarrier();
        ship.addBoardObserver(player.getPlayerBoard());
        break;
      default:
        ship = null;
        break;

    }

    return ship;
  }

}

package edu.bu.met.cs665;

import java.util.Scanner;

public class BattleShip {

  Player player1;
  Player player2;

  // Create scanner with explicit encoding
  static Scanner s = new Scanner(System.in, "UTF-8");

  public static void main(String[] args) {

    BattleShip bs = new BattleShip();

    bs.playerSetUp();
    bs.shipSelection();
    bs.playGame();

  }

  private void playerSetUp() {
    System.out.println("Welcome to BattleShip!");
    // Create a new player 1
    System.out.println("Player 1 enter your name!");
    player1 = new Player(UserInput.getName(s));

    // Create a new player 2
    System.out.println("Player 2 enter your name!");
    player2 = new Player(UserInput.getName(s));
  }

  private void shipSelection() {

    // Ask how many ships that would like to play with
    System.out.println("How many ships would you like to play with?");
    int ships = UserInput.getNumShips(s);

    player1.getPlayerBoard().setShipsRemaining(ships);
    player2.getPlayerBoard().setShipsRemaining(ships);

    // ask player 1 to set their ships
    System.out.println(player1.getPlayerName() + " , place your ships!");
    setShips(ships, player1);

    // ask player 2 to set their ships
    System.out.println(player2.getPlayerName() + " , place your ships!");
    setShips(ships, player2);
  }

  // this variant of the game allows for different shapes for the ships
  // as long as each square of a ship touches at least one other square
  // no diagonals are allowed
  // https://en.wikipedia.org/wiki/Battleship_(game)#Variations
  // since board is 5x5 this makes game more fun!
  private void setShips(int numShips, Player player) {

    Ship ship;

    for (int i = 1; i < numShips + 1; i++) {
      switch (i) {
        case 1:
          ship = ShipFactory.getShip("Destroyer", player);
          System.out.println("Where would you like to place your Destroyer?");
          System.out.println("Select 2 adjacent locations");
          setShipHelper(player, ship);

          break;
        case 2:
          ship = ShipFactory.getShip("Submarine", player);
          System.out.println("Where would you like to place your Submarine?");
          System.out.println("Select 3 adjacent locations");
          setShipHelper(player, ship);
          break;
        case 3:
          ship = ShipFactory.getShip("Cruiser", player);
          System.out.println("Where would you like to place your Cruiser?");
          System.out.println("Select 3 adjacent locations");
          setShipHelper(player, ship);
          break;
        case 4:
          ship = ShipFactory.getShip("Battleship", player);
          System.out.println("Where would you like to place your BattleShip?");
          System.out.println("Select 4 adjacent locations");
          setShipHelper(player, ship);
          break;
        case 5:
          ship = ShipFactory.getShip("Carrier", player);
          System.out.println("Where would you like to place your Carrier?");
          System.out.println("Select 5 adjacent locations");
          setShipHelper(player, ship);
          break;
        default:
          break;
      }
    }
  }

  public void setShipHelper(Player player, Ship ship) {
    int[] previousLocation = null;
    for (int i = 0; i < ship.getShipSize(); i++) {
      // while loop until all checks on input complete
      while (true) {
        System.out.println("Select a new location!");
        boolean occupied = true;
        int[] xy = UserInput.getGridLocation(s);
        ControlTower ct = player.getPlayerBoard().boardGrid[xy[0]][xy[1]];

        // check if the space is already occupied
        if (ct.checkOccupied()) {
          System.out.println("That location already has a ship!");
        } else {
          occupied = false;
        }
        // check if the space is adjacent
        boolean nextToShip = player.getPlayerBoard().checkAdjacent(xy, previousLocation);
        if (!nextToShip) {
          System.out.println("You must select an adjacent location to your previous selection");
        }
        // all checks pass
        if (!occupied && nextToShip) {
          // set the ship
          ct.addShip(ship);
          // update previous location
          previousLocation = new int[2];
          previousLocation[0] = xy[0];
          previousLocation[1] = xy[1];
          System.out.println("Good idea for a location!");
          break;
        }
      }

    }

  }

  public void playGame() {
    // only break when Game Over
    while (true) {
      // player1 takes the first shot
      System.out.println(player1.getPlayerName() + " take a shot!");
      int[] player1shot = UserInput.getGridLocation(s);

      // check shot - a game over will return true
      if (registerShot(player2, player1shot)) {
        break;
      }

      // display player2 board - only reveal empty, hit, or missed locations
      System.out.println("Updated board looks like:");
      player2.getPlayerBoard().displayBoard();

      // player2 takes the next shot
      System.out.println(player2.getPlayerName() + " take a shot!");
      int[] player2shot = UserInput.getGridLocation(s);

      // check shot - a game over will return true
      if (registerShot(player1, player2shot)) {
        break;
      }

      // display player1 board - only reveal empty, hit, or missed locations
      System.out.println("Updated board looks like:");
      player1.getPlayerBoard().displayBoard();

    }
  }

  public boolean registerShot(Player player, int[] shot) {
    boolean gameOver = false;

    ControlTower ct = player.getPlayerBoard().boardGrid[shot[0]][shot[1]];

    // update location if it is a miss
    if (ct.checkEmpty()) {
      System.out.println("That's a miss!");
      ct.setLocationState(new MissedLocation());

      // update location if it is a hit
    } else if (ct.checkOccupied()) {
      System.out.println("That's a hit!");
      ct.setLocationState(new HitLocation());
      // decrement the ship life
      ct.getShip().decrementHealth();
      if (gameOver(player)) {
        System.out.println("You destroyed all " + player.getPlayerName() + "'s ships! Game Over!");
        // game over
        gameOver = true;
      }

      // tell the player to be more careful if they are selecting already hit or
      // missed locations
    } else if (ct.checkHit() || ct.checkMissed()) {
      System.out.println("You already selected that location! Be more careful in your selections!");
    }

    return gameOver;
  }

  public boolean gameOver(Player player) {
    if (player.getPlayerBoard().shipsRemaining == 0) {
      return true;
    } else {
      return false;
    }
  }

}

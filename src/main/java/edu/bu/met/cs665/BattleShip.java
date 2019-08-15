package edu.bu.met.cs665;


import java.util.Scanner;

public class BattleShip{

    Player player1;
	Player player2;
	
	boolean gameOver = false;

	static Scanner s = new Scanner(System.in);

	public static void main(String[] args) {

		BattleShip bs = new BattleShip();
		
		bs.playerSetUp();
		bs.shipSelection();
		bs.playGame();

	}
	
	
	private void playerSetUp() {
		System.out.println("Welcome to BattleShip!");
		//Create a new player 1
		System.out.println("Player 1 enter your name!");
		player1 = new Player(UserInput.getName(s));
		
		//Create a new player 2
		System.out.println("Player 2 enter your name!");
		player2 = new Player(UserInput.getName(s));
	}
	
	private void shipSelection() {
		
		//Ask how many ships that would like to play with
		System.out.println("How many ships would you like to play with?");
		int ships = UserInput.getNumShips(s);
		
		player1.getPlayerBoard().setShipsRemaining(ships);
		player2.getPlayerBoard().setShipsRemaining(ships);
		
		//ask player 1 to set their ships
		System.out.println(player1.getPlayerName() + " , place your ships!");
		setShips(ships, player1);
		

		//ask player 2 to set their ships
		System.out.println(player2.getPlayerName() + " , place your ships!");
		setShips(ships, player2);
	}

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
				ship = ShipFactory.getShip("Submarine", player);
				System.out.println("Where would you like to place your Carrier?");
				System.out.println("Select 5 adjacent locations");
				setShipHelper(player, ship);
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

				// check if the space is already occupied
				if (player.getPlayerBoard().checkOccupied(xy)) {
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
					// set the ship - so yeah I don't want a brand new ship, I want to use the ship I have
					player.getPlayerBoard().boardGrid[xy[0]][xy[1]].addShip(ship);
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
			//player1 takes the first shot
			System.out.println("Player 1 take a shot!");
			int[] shot = UserInput.getGridLocation(s);
			
			//check shot
			//update location if it is a miss
			if (player2.getPlayerBoard().checkEmpty(shot)) {
				//maybe I should change the checkOccupied method and a set_State method to the ControlTower instead
				System.out.println("That's a miss!");
				player2.getPlayerBoard().boardGrid[shot[0]][shot[1]].getLocation().set_state(new MissedLocation());
			}
			
			//update location if it is a hit
			else if (player2.getPlayerBoard().checkOccupied(shot)) {
				System.out.println("That's a hit!");
				player2.getPlayerBoard().boardGrid[shot[0]][shot[1]].getLocation().set_state(new HitLocation());
				// decrement the ship life
				player2.getPlayerBoard().boardGrid[shot[0]][shot[1]].getShip().decrementHealth();
				if (gameOver(player2)) {
					System.out.println("You destroyed all " + player2.getPlayerName() + "'s ships! Game Over!");
					// add methods to play the game over below gameOver method
					break;
				}
				
			}
			
			//tell the player to be more careful if they are selecting already hit or missed locations
			else if (player2.getPlayerBoard().checkHit(shot) || player2.getPlayerBoard().checkMissed(shot)) {
				System.out.println("You already selected that location! Be more careful in your selections!");
			}
			
			//display player2 board - only reveal empty, hit, or missed locations
			System.out.println("Updated board looks like:");
			player2.getPlayerBoard().displayBoard();
			
			//player2 takes the next shot
			System.out.println("Player 2 take a shot!");
			int[] shot2 = UserInput.getGridLocation(s);
			
			//check shot
			//update location if it is a miss
			if (player1.getPlayerBoard().checkEmpty(shot2)) {
				//maybe I should change the checkOccupied method and a set_State method to the ControlTower instead
				System.out.println("That's a miss!");
				player1.getPlayerBoard().boardGrid[shot2[0]][shot2[1]].getLocation().set_state(new MissedLocation());
			}
			
			//update location if it is a hit
			else if (player1.getPlayerBoard().checkOccupied(shot2)) {
				System.out.println("That's a hit!");
				player1.getPlayerBoard().boardGrid[shot2[0]][shot2[1]].getLocation().set_state(new HitLocation());
				// decrement the ship life
				player1.getPlayerBoard().boardGrid[shot2[0]][shot2[1]].getShip().decrementHealth();
				if (gameOver(player1)) {
					System.out.println("You destroyed all " + player1.getPlayerName() + "'s ships! Game Over!");
					// add methods to play the game over below gameOver method
					break;
				}
				
			}
			
			//tell the player to be more careful if they are selecting already hit or missed locations
			else if (player1.getPlayerBoard().checkHit(shot2) || player1.getPlayerBoard().checkMissed(shot2)) {
				System.out.println("You already selected that location! Be more careful in your selections!");
			}
			
			//display player1 board - only reveal empty, hit, or missed locations
			System.out.println("Updated board looks like:");
			player1.getPlayerBoard().displayBoard();
			
		}
	}
	
	public boolean gameOver(Player player) {
		if (player.getPlayerBoard().shipsRemaining == 0) {
			return true;
		}
		else {
			return false;
		}
	}

}

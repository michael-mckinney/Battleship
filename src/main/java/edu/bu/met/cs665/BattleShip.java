package edu.bu.met.cs665;

import java.util.Scanner;

public class BattleShip {

	static Player player1;
	Player player2;

	static Scanner s = new Scanner(System.in);

	public static void main(String[] args) {

		// add playGame method which consists of while loop

		// should also have a checkGameOver method

		BattleShip bs = new BattleShip();

		bs.shipSelection();

	}
	
	private void shipSelection() {
		System.out.println("Welcome to BattleShip!");
		
		//Ask how many ships that would like to play with
		System.out.println("How many ships would you like to play with?");
		int ships = UserInput.getNumShips(s);
		
		//Create a new player 1
		System.out.println("Player 1 enter your name!");
		player1 = new Player(UserInput.getName(s));
		//ask player 1 to set their ships
		System.out.println(player1.getPlayerName() + " , place your ships!");
		setShips(ships, player1);
		
		//Create a new player 2
		System.out.println("Player 2 enter your name!");
		player2 = new Player(UserInput.getName(s));
		//ask player 2 to set their ships
		System.out.println(player2.getPlayerName() + " , place your ships!");
		setShips(ships, player2);
	}

	private void setShips(int numShips, Player player) {

		for (int i = 1; i < numShips + 1; i++) {
			switch (i) {
			case 1:				
				System.out.println("Where would you like to place your Destroyer?");
				System.out.println("Select 2 adjacent locations");
				//initialize previousLocation to null
				int[] previousLocation1 = null;
				// need to set 2 spots for a Destroyer
				for (int j = 0; j < 2; j++) {
					previousLocation1 = setShipHelper(player, previousLocation1);
				}
				break;
			case 2: 
				System.out.println("Where would you like to place your Submarine?");
				System.out.println("Select 3 adjacent locations");
				//initialize previousLocation to null
				int[] previousLocation2 = null;
				// need to set 3 spots for a Submarine
				for (int j = 0; j < 3; j++) {
					previousLocation2 = setShipHelper(player, previousLocation2);
				}
				break;
			case 3: 
				System.out.println("Where would you like to place your Cruiser?");
				System.out.println("Select 3 adjacent locations");
				//initialize previousLocation to null
				int[] previousLocation3 = null;
				// need to set 3 spots for a Cruiser
				for (int j = 0; j < 3; j++) {
					previousLocation3 = setShipHelper(player, previousLocation3);
				}
				break;
			case 4: 
				System.out.println("Where would you like to place your BattleShip?");
				System.out.println("Select 4 adjacent locations");
				//initialize previousLocation to null
				int[] previousLocation4 = null;
				// need to set 4 spots for a BattleShip
				for (int j = 0; j < 4; j++) {
					previousLocation4 = setShipHelper(player, previousLocation4);
				}
				break;
			case 5: 
				System.out.println("Where would you like to place your Carrier?");
				System.out.println("Select 5 adjacent locations");
				//initialize previousLocation to null
				int[] previousLocation5 = null;
				// need to set 5 spots for a Carrier
				for (int j = 0; j < 5; j++) {
					previousLocation5 = setShipHelper(player, previousLocation5);
				}
				break;
			}
		}
	}
	
	public int[] setShipHelper(Player player, int[] previousLocation) {
		// would love to make this static
		UserInput input = new UserInput();
		
		
		// while loop until all checks on input complete
		while (true) {
			System.out.println("Select a new location!");
			boolean occupied = true;
			int[] xy = input.getGridLocation(s);

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
				// set the ship
				player.getPlayerBoard().boardGrid[xy[0]][xy[1]].addShip(new ShipDestroyer());
				// update previous location
				previousLocation = new int[2];
				previousLocation[0] = xy[0];
				previousLocation[1] = xy[1];
				System.out.println("Good idea for a location!");
				break;
			}
		}
		return previousLocation;
	}

}

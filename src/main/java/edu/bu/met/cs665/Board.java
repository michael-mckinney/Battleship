package edu.bu.met.cs665;

import java.util.Observable;
import java.util.Observer;

public class Board implements Observer {

	ControlTower[][] boardGrid = new ControlTower[5][5];
	int shipsRemaining = -1;

	// do this in the constructor instead I think
	public Board() {
		// iterate through boardGrid and add Locations
		for (int row = 0; row < boardGrid.length; row++) {
			for (int col = 0; col < boardGrid[row].length; col++) {
				boardGrid[row][col] = new ControlTower();
			}
		}
	}

	// set to number of ships users are playing with
	public void setShipsRemaining(int num) {
		this.shipsRemaining = num;
	}

	public boolean checkOccupied(int[] xy) {
		if (boardGrid[xy[0]][xy[1]].getLocation().getStatus().contentEquals("Occupied")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkEmpty(int[] xy) {
		if (boardGrid[xy[0]][xy[1]].getLocation().getStatus().contentEquals("Empty")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkHit(int[] xy) {
		if (boardGrid[xy[0]][xy[1]].getLocation().getStatus().contentEquals("Hit")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkMissed(int[] xy) {
		if (boardGrid[xy[0]][xy[1]].getLocation().getStatus().contentEquals("Missed")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkAdjacent(int[] xy, int[] previousLocation) {
		boolean nextToShip = false;
		boolean rowOK = true;
		boolean colOK = true;
		// check if location chosen is next to the previous location - no diagonals
		if (previousLocation != null) {
			// check row is ok
			if (previousLocation[0] <= xy[0] + 1 && previousLocation[0] >= xy[0] - 1) {
				rowOK = true;
			} else {
				rowOK = false;
			}
			// check column is ok
			if (previousLocation[1] <= xy[1] + 1 && previousLocation[1] >= xy[1] - 1) {
				colOK = true;
			} else {
				colOK = false;
			}
			// check that it is not diagonally placed
			if (Math.abs(previousLocation[0] - xy[0]) + Math.abs(previousLocation[1] - xy[1]) > 1) {
				colOK = false;
				rowOK = false;
			}
		}

		// verify all checks pan out
		if (rowOK && colOK) {
			nextToShip = true;
		}

		return nextToShip;
	}

	@Override
	public void update(Observable o, Object obj) {
		// decrement number of ships remaining
		String shiptype = ((Ship) obj).getShipName();
		System.out.println("You sunk a " + shiptype);
		--this.shipsRemaining;

	}

	public void displayBoard() {
		for (int row = 0; row < boardGrid.length; row++) {
			System.out.println();
			for (int col = 0; col < boardGrid[row].length; col++) {
				System.out.printf(boardGrid[row][col].getLocation().getDisplay());
			}
		}
		System.out.println();
	}
}

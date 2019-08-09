package edu.bu.met.cs665;


import java.util.Scanner;

//this class has methods that require the scanner to be passed in
// scanner should be closed from the calling class
public class UserInput {
	
	public static int getNumShips(Scanner s) {
		
		int numShips;
		
		while (true) {
			System.out.println("Enter number of ships between 1 and 5");
            String input = s.next();
            try {
                numShips = Integer.parseInt(input);
                if (numShips > 0 && numShips < 6) {
                	break;
                }
                else {
                	System.out.println("Value must be between 1 and 5");
                }

            } catch (NumberFormatException ne) {
                System.out.println("Input is not an integer, continue");
            }  
		}
		
		
		return numShips;
		
	}
	
	public int[] getGridLocation(Scanner s) {
		
		int[] gridLocation = new int[2]; 
		int row;
		int col;
				
		
		while (true) {
			System.out.println("Enter row as an integer between 1 and 5");
            String input = s.next();
            try {
                row = Integer.parseInt(input);
                if (row > 0 && row < 6) {
                	break;
                }
                else {
                	System.out.println("Value must be between 1 and 5");
                }

            } catch (NumberFormatException ne) {
                System.out.println("Input is not an integer, continue");
            }  
        }
		
		while (true) {
			System.out.println("Enter column as an integer between 1 and 5");
            String input = s.next();
            try {
                col = Integer.parseInt(input);
                if (col > 0 && col < 6) {
                	break;
                }
                else {
                	System.out.println("Value must be between 1 and 5");
                }

            } catch (NumberFormatException ne) {
                System.out.println("Input is not an integer, continue");
            }  
        }
		
		gridLocation[0] = row - 1;
		gridLocation[1] = col -1;
		
		return gridLocation;

		
		
	}

}

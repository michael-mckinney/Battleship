package edu.bu.met.cs665;

import java.util.Observable;

public abstract class Ship extends Observable{
	
	protected String shipName;
	protected int shipSize;
	protected int shipHealth;
	
	//get methods
	protected String getShipName() {
		return this.shipName;
	}
	
	protected int getShipSize() {
		return this.shipSize;
	}
	
	//if a subclass has this method called, it its own variable updated?
	public void decrementHealth() {
		shipHealth = --shipHealth;
		//check if ship is destroyed and notify if it is
		if (shipHealth == 0) {
			setChanged();
			//do I have to pass an object?
			notifyObservers();
		}
	}
	
	
}

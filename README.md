# Project Description

For this project I used multiple design patterns in implementing the Battleship game. The game is simple. It is a 2 player game that uses a 5 x 5 grid. The number of ships that will be played with is selected by the user, and ranges from 1 to 5.

The players place their ships depending on their input into how many ships will be used in that game. The rules for placing a ship are that you cannot place on an already occupied square, and each part of a ship must be adjacent to another part of the same ship. Diagonals are not allowed, but the ship does not have to be in the shape of a straight line. Since the board is 5x5, this game uses a variation that allows ships to be any sort of shape. See the following for more about the battleship game and possible variations on it: https://en.wikipedia.org/wiki/Battleship_(game)#Variations.

Finally, each player takes a single shot and the result of that shot is displayed, including whether it was a miss, or whether it was a hit and if a ship was sunk and if all the ships are sunk and the game is over. The board is displayed to each player after each shot.


# Design Patterns

I used three explicit design patterns in my project: State, Observer, and Factory. I will go over each design pattern used and the benefits for using that design pattern. The overall design goals in my project were to allow for flexibility to add or remove new classes, to allow for simplicity and understandability, and to follow good principles such as DRY, and the principle of high cohesion and low coupling. Following these design goals allows for increased maintainability and extendability of the project.

A high level overview of my code structure is as follows:

A Player object is associated with a Board, and a new Board is created upon constructing a Player. A Board has a field for a 2D array, which, when the Board is constructed, is filled with ControlTower objects. ControlTower objects are associated with both a Ship, and a Location and handle the client's communication to specific ships and locations.

Finally, I have a Battleship class which contains the main method, and which uses classes like UserInput and ShipFactory, as well as the Player objects, to get user input, create Ships, place the Ships, and register shots. Below is an overall class diagram:

![Class Diagram](https://user-images.githubusercontent.com/15668269/63188785-38b9eb00-c017-11e9-9816-e20a9bd9ea28.PNG)

**Factory Pattern** 

I used the Factory Method pattern to allow for a single factory to build multiple types of ships, with the type of ship being determined at run time. I have a base Ship which is abstract, and then multiple different Concrete types such as ShipSubmarine, and ShipCruiser. Each of these ships have a different name, size, and initial health.

Since the user can decide how many ships they want in the game, it is not the case that each type of ship will always be created. Ship creation is determined at run time. The main BattleShip class has the method setShips(), which iterates through the number of ships the user decided to create and calls the ShipFactory to create a ship as required.

```java
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
          ...
```

 By using the factory method I can easily add different types of ships, and the only changes are to add a new concrete Ship class, and to add the logic to the ShipFactory class to handle instantiation of that type. It is simple and understandable for the client to simply call the ShipFactory.getShip() method and pass in the new type that is required to be created.   


 **State Pattern**  
 
I used the state pattern to allow the Location object to alter its behavior when its internal state changes. For example, a Location can have an internal state of being Empty, or Occupied, or Hit or Missed. Each of these states results in a different status, and a different display when the board is displayed. Therefore, I created the LocationState interface, with multiple implementations such as EmptyLocation, HitLocation etc. Each of these objects implement the getStatus() and getDisplay() methods. 

The constructor for the Location class initially sets the LocationState field to a new EmptyLocation. It also contains the following methods:

```java
  public void set_state(LocationState state) {
    currentState = state;
  }

  public String getStatus() {
    return currentState.getStatus();
  }

  public String getDisplay() {
    return currentState.getDisplay();
  }
```

This allows for both the state of a Location to be updated (changing an Occupied Location to a Hit Location for example), and for the current status and display for each location to be retrieved. For example, the Board class has a method displayBoard(). This method iterates through each ControlTower object and gets the status of the associated Location. This allows the avoidance of multiple conditional checks in the getDisplay() method. 

It is simple to update the state of a Location from the ControlTower class, so any class that has access to a Player and the associated Board can access a specific ControlTower and call a method to update the state of the associated Location. It is also simple to check the state of a Location when a player takes a shot, and then update the state accordingly. This allows for decreased duplicated code, and increased simplicity and understandability. If a new type of state is required, it is simple to add a class that implements the LocationState interface. 

 **Observer Pattern** 
 
One problem that I needed to solve was how to notify interested classes that a certain Ship was destroyed. I decided upon utilizing the Observer pattern. Usually the Observer pattern involves one Observable, with multiple Observers. However, this pattern is flexible, and can be used to meet the desired use case. Upon further research, I came to the conclusion that the pattern does allow for flexibility to allow for multiple Observables, each having a single Observer. In my case, each created Ship is an Observable, and the Board object is a registered Observer for each created Ship. 
 
 When a ship is created in the ShipFactory, the Player's associated Board instance is added as an Observer. When an Location with an OccupiedState is hit, the Ship's health is decremented, and if the health is completely depleted, the setChanged() and notifyObservers() methods are called. The Board class then has the following update() method:
 
   
 ```java
  @Override
  public void update(Observable o, Object obj) {
    // decrement number of ships remaining
    String shiptype = ((Ship) obj).getShipName();
    System.out.println("You sunk a " + shiptype);
    --this.shipsRemaining;
  }  
```

Using the Observer pattern allows for the Board and specific Ship instance to not be tightly coupled. It decreases duplicated code, and increases the simplicity and understandability of the project implementation.





# How to compile the project

You can use Apache Maven to compile and run this project. 

You need to install Apache Maven (https://maven.apache.org/)  on your system. 

Type on the command line: 

```bash
mvn clean compile
```

# How to create a binary runnable package 


```bash
mvn clean compile assembly:single
```


# How to run

```bash
mvn -q clean compile exec:java -Dexec.executable="edu.bu.met.cs665.BattleShip" -Dlog4j.configuration="file:log4j.properties"
```

I recommend the above command for running the project. 

Alternatively, you can run the following command. It will generate a single jar file with all of the dependencies. 

```bash
mvn clean compile assembly:single

java -Dlog4j.configuration=file:log4j.properties -classpath ./target/JavaProjectTemplate-1.0-SNAPSHOT-jar-with-dependencies.jar  edu.bu.met.cs665.BattleShip
```


# Run all the unit test classes.


```bash
mvn clean compile test

```

# Using Findbugs 

To see bug detail using the Findbugs GUI, use the following command "mvn findbugs:gui"

Or you can create a XML report by using  


```bash
mvn findbugs:gui 
```

or 


```bash
mvn findbugs:findbugs
```


For more info about FindBugs see 

http://findbugs.sourceforge.net/

And about Maven Findbug plugin see 
https://gleclaire.github.io/findbugs-maven-plugin/index.html


You can install Findbugs Eclipse Plugin 

http://findbugs.sourceforge.net/manual/eclipse.html



SpotBugs https://spotbugs.github.io/ is the spiritual successor of FindBugs.


# Run Checkstyle 

CheckStyle code styling configuration files are in config/ directory. Maven checkstyle plugin is set to use google code style. 
You can change it to other styles like sun checkstyle. 

To analyze this example using CheckStyle run 

```bash
mvn checkstyle:check
```

This will generate a report in XML format


```bash
target/checkstyle-checker.xml
target/checkstyle-result.xml
```

and the following command will generate a report in HTML format that you can open it using a Web browser. 

```bash
mvn checkstyle:checkstyle
```

```bash
target/site/checkstyle.html
```




// TODO what happens if both go to zero??
package csci.ooad;


import java.util.Random;
import java.util.Scanner;

public class Maze {

    private static final String[] CREATURE_TYPES = {"Ogre", "Goblin", "Troll", "Werewolf", "Vampire", "Gnome", "Zombie"};

    // fields
    private int turnCount;
    private Creature creature;
    private Adventurer adventurer;
    private Room [] rooms;
    private Dice dice;
    private Character winner;


    // constructor

    /**
     * Constructor to create the maze with 4 rooms and set turn count to 0
     */
    public Maze() {
        this.turnCount = 0;
        createRooms();
        winner = null;
    }

    // methods


    public Room[] getRooms() {
        return rooms;
    }

    /**
     * createRooms: creates 4 rooms (NW, NE, SE, SW)
     */
    private void createRooms() {
        // Initialize the rooms array with 4 rooms (Northwest, Northeast, Southeast, Southwest)
        rooms = new Room[4];

        // Create and assign rooms to the respective positions
        rooms[0] = new Room("Northwest");  // Empty occupants for now
        rooms[1] = new Room("Northeast");
        rooms[2] = new Room("Southeast");
        rooms[3] = new Room("Southwest");
    }

    /**
     * beginGame: method to start the game (call maze constructor, ask for user input for adventurer name,
     * randomly selects type of creature, creates creature and adventure objects, and randomly places each
     * in one of the 4 rooms). Calls the takeTurn method until a player dies (health = 0)
     */
    public void beginGame() {
        // Print that the game is starting
        System.out.println("Starting the game...");

        // create the maze
        Maze maze = new Maze();
        System.out.println("Maze created with 4 rooms.");

        // get user input for adventurer's name
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter adventurer's name: ");
        String adventurerName = scanner.nextLine();
        System.out.println("Adventurer's name is: " + adventurerName);

        // randomly select a creature type
        Random random = new Random();
        String randomCreatureType = CREATURE_TYPES[random.nextInt(CREATURE_TYPES.length)];
        System.out.println("Randomly selected creature: " + randomCreatureType);

        // create Creature and Adventurer objects
        Creature creature = new Creature(randomCreatureType);
        Adventurer adventurer = new Adventurer(adventurerName);
        System.out.println("Created adventurer: " + adventurer);
        System.out.println("Created creature: " + creature);

        // Randomly place each character in one of the rooms (they can be placed in the same room)
        int randomRoomIndexForAdventurer = random.nextInt(rooms.length); // Random room for adventurer
        int randomRoomIndexForCreature = random.nextInt(rooms.length);   // Random room for creature

        // Set the room index for adventurer and creature
        adventurer.updateRoomIndex(randomRoomIndexForAdventurer);
        creature.updateRoomIndex(randomRoomIndexForCreature);

        // Add adventurer to a random room
        rooms[randomRoomIndexForAdventurer].addOccupant(adventurer);
        System.out.println("Adventurer placed in: " + rooms[randomRoomIndexForAdventurer].getName());

        // Add creature to a random room (could be the same room)
        rooms[randomRoomIndexForCreature].addOccupant(creature);
        System.out.println("Creature placed in: " + rooms[randomRoomIndexForCreature].getName());

        // Print the initial state of the game (optional)
        System.out.println("\nInitial game state:");

//        for (Room room : rooms) {
//            System.out.println(room.toString());
//        }
        printMaze();


        // continue taking turns until one of the character's health = 0
        System.out.println("\nGame is ready. Adventurer and creature are placed in rooms.");
        System.out.println("Starting turns.");

        // call takeTurn while both creatures are alive
        while (creature.getHealth() > 0 && adventurer.getHealth() > 0) {
            takeTurn(adventurer, creature);
        }

        // getting here means one of the characters has died --> end game
        // get which creature died and print that out
        // TODO add check for which score is higher if both below 0?? no winner??
        if (creature.getHealth() <= 0 && adventurer.getHealth() <= 0) {
            System.out.println("Both players die. Game over.\n");
        }
        else if (creature.getHealth() <= 0) {
            creature.setWin();
            winner = creature;
            System.out.println("Boo, the creature won. Game over.\n");
        }
        else {
            adventurer.setWin();
            winner = adventurer;
            System.out.println("Adventurer " + adventurerName + " has defeated the creature! Game over");
        }
        // System.out.println("A character has died. Game over.\n");

        System.out.println("Exited beginGame method.\n");

    }

    /**
     * takeTurn: Prints maze and turn number. Analyzes the room locations of the creature and adventurer
     * and calls the fight method if they are in the same room. Otherwise moves the adventurer to a random neighboring room
     * Increments turnCount
     */
    public void takeTurn(Adventurer adventurer, Creature creature) {

        turnCount++; // increment turn count
        printMaze();  // Print current state of the maze

        // if both players are in the same room, call fight method
        if (areBothInSameRoom(adventurer, creature)) {
            fight(adventurer, creature);
            // both characters lose 0.5 points, regardless of fight outcome
//            System.out.println("Both players lose 0.5 health after fight.");
            adventurer.subtractFromHealth(-0.5);
            creature.subtractFromHealth(-0.5);
            System.out.println("Health status after fight: ");
            System.out.println("Adventurer: " + adventurer.getHealth());
            System.out.println("Creature: " + creature.getHealth());

        } else { // if both players are not in the same room, move adventurer to a random neighboring room
            System.out.println("Adventurer and creature are not in same room. Adventurer moves rooms and loses 0.25 health.\n");
            moveAdventurer(adventurer);  // Move adventurer to a neighboring room
        }
    }

    /**
     * areBothInSameRoom: method to check if both adventurer and creature are in the same room
     * @return true if adventurer and creature are in same room
     */
    private boolean areBothInSameRoom(Adventurer adventurer, Creature creature) {
        // return true if the room index for adventurer and creature are the same
        if (adventurer.getRoomIndex() ==  creature.getRoomIndex()) {
            return true;
        }
        else {
            return false;
        }
    }


    /**
     * printMaze: prints the maze in the following (example) format:
     * Polymorphia MAZE: turn 1
     * Northwest:
     * Northeast:
     * Creature Ogre(health: 3) is here
     * Southwest:
     * Adventurer Bill(health: 5) is here
     * Southeast:
     */
    public void printMaze() {
        System.out.println();
        if (turnCount > 0) {
            System.out.println("Polymorphia MAZE: turn " + turnCount);
        }
        for (Room room : rooms) {
            System.out.println(room.toString());
        }
    }

    /**
     * moveAdventurer: moves the adventurer to a random neighboring room
     * neighboring room = roomIndex +- 1
     * ensure index is in range of rooms array
     */
    public void moveAdventurer(Adventurer adventurer) {
        // Get the current room index of the adventurer
        int currentRoomIndex = adventurer.getRoomIndex();

        // Randomly decide whether to move to the next room (index + 1) or previous room (index - 1)
        Random random = new Random();
        int direction = random.nextBoolean() ? 1 : -1; // 1 for next, -1 for previous

        // Calculate the new room index using modulus to wrap around
        int newRoomIndex = (currentRoomIndex + direction + rooms.length) % rooms.length;

        // remove the adventurer from the current room, update index, add to new room
        rooms[currentRoomIndex].removeAdventurer(adventurer);
        adventurer.updateRoomIndex(newRoomIndex);
        rooms[newRoomIndex].addOccupant(adventurer);

        // decrement adventurer's health by 0.25
        adventurer.subtractFromHealth(-0.25);

        System.out.println("Adventurer moved to " + rooms[newRoomIndex].getName() + ".");
    }

    /**
     * fight: calls Character.rollDie() for Creature and Adventurer. Character with lower roll takes damage
     * equal to the difference in the rolls.
     */
    public void fight(Adventurer adventurer, Creature creature) {
        System.out.println("The adventurer and the creature are in the same room! A fight breaks out!\n");

        // adventurer and creature both roll a die
        int adventurerRoll = adventurer.rollDie();
        int creatureRoll = creature.rollDie();

        System.out.println("Adventurer rolls: " + adventurerRoll);
        System.out.println("Creature rolls: " + creatureRoll);

        // compare the rolls, character with lower roll will take damage equal to the difference between the rolls
        // if both rolls are the same, neither character takes damage
        if (adventurerRoll == creatureRoll) { // if both rolls are the same, nothing happens
            System.out.println("Fight is a tie!");
        } else if (adventurerRoll > creatureRoll) { // adventurer wins, subtract the difference from the creature's health
            int damage = adventurerRoll - creatureRoll;
            creature.subtractFromHealth(-damage);  // take damage (as a negative number)
            System.out.println("Adventurer wins the round. Creature takes " + damage + " damage.");
        } else { // creature wins, subtract the difference from the adventurer's health
            int damage = creatureRoll - adventurerRoll;
            adventurer.subtractFromHealth(-damage);  // take damage (as a negative number)
            System.out.println("Creature wins the round. Adventurer takes " + damage + " damage.");
        }
    }


    public Character getWinner() {
        return winner;
    }


}

// TODO: imports
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


    // constructor

    /**
     * Constructor to create the maze with 4 rooms and set turn count to 0
     */
    public Maze() {
        this.turnCount = 0;
        createRooms();
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

        // Add adventurer to a random room
        rooms[randomRoomIndexForAdventurer].addOccupant(adventurer);
        System.out.println("Adventurer placed in: " + rooms[randomRoomIndexForAdventurer].getName());

        // Add creature to a random room (could be the same room)
        rooms[randomRoomIndexForCreature].addOccupant(creature);
        System.out.println("Creature placed in: " + rooms[randomRoomIndexForCreature].getName());

        // Print the initial state of the game (optional)
        System.out.println("\nInitial game state:");

        for (Room room : rooms) {
            System.out.println(room.toString());
        }


        // continue taking turns until one of the character's health = 0
        System.out.println("Game is ready. Adventurer and creature are placed in rooms.");
        System.out.println("Exited beginGame method.");
    }

    /**
     * takeTurn: Prints maze and turn number. Analyzes the room locations of the creature and adventurer
     * and calls the fight method if they are in the same room. Otherwise moves the adventurer to a random neighboring room
     * Increments turnCount
     */
//    public void takeTurn() {
//
//        turnCount++; // increment turn count
//        printMaze();  // Print current state of the maze
//
//        // if both players are in the same room, call fight method
//        if (adventurerRoom.equals(creatureRoom)) { // TODO: might want to do this as Adventure.getRoom() and have room be a field in the Character class
//            fight();
//        } else { // if both players are not in the same room, move adventurer to a random neighboring room
//            moveAdventurer();  // Move adventurer to a neighboring room
//        }
//    }


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

    /**
     * moveAdventurer: moves the adventurer to a random neighboring room
     */

    /**
     * fight: calls Character.rollDie() for Creature and Adventurer. Character with lower roll takes damage
     * equal to the difference in the rolls.
     */
    public void fight() {
        System.out.println("The adventurer and the creature are in the same room! A fight breaks out!");

        // adventurer and creature both roll a die
        int adventurerRoll = adventurer.rollDie();
        int creatureRoll = creature.rollDie();

        // compare the rolls, character with lower roll will take damage equal to the difference between the rolls
        // if both rolls are the same, neither character takes damage
        if (adventurerRoll == creatureRoll) { // if both rolls are the same, nothing happens
            System.out.println("Fight is a tie! No damage to either player this turn.");
        } else if (adventurerRoll > creatureRoll) { // adventurer wins, subtract the difference from the creature's health
            int damage = adventurerRoll - creatureRoll;
            creature.subtractFromHealth(-damage);  // take damage (as a negative number)
            System.out.println("Adventurer wins the round. Creature takes " + damage + " .");
        } else { // creature wins, subtract the difference from the adventurer's health
            int damage = creatureRoll - adventurerRoll;
            adventurer.subtractFromHealth(-damage);  // take damage (as a negative number)
            System.out.println("Creature wins the round. Adventurer takes " + damage + " .");
        }
    }


}

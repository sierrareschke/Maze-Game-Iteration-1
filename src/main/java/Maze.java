// TODO: imports

import java.util.Random;
import java.util.Scanner;

public class Maze {

    // fields
    // turnCount initially set to 0
    private int turnCount;
    private static final String[] ROOM_NAMES = {"northwest", "northeast", "southeast", "southwest"}; // room names: NW NE SE SW
    // TODO - not sure if this is how we want to initialize rooms (above) (aka as static final vs in constructor)
    private static final String[] CREATURE_TYPES = {"Ogre", "Goblin", "Troll", "Werewolf", "Vampire", "Gnome", "Zombie"};
    private Creature creature;
    private Adventurer adventurer;
    private String creatureRoom;
    private String adventurerRoom;

    // constructor

    /**
     * Constructor to create the maze with 4 rooms and set turn count to 0
     */
    public Maze() {
        this.turnCount = 0;
        createRooms();
    }

    // methods

    /**
     * createRooms: creates 4 rooms (NW, NE, SE, SW)
     */
    private void createRooms() {
        // TODO: how do we want to create each room and how to specify neighbors?
    }

    /**
     * beginGame: method to start the game (call maze constructor, ask for user input for adventurer name,
     * randomly selects type of creature, creates creature and adventure objects, and randomly places each
     * in one of the 4 rooms). Calls the takeTurn method until a player dies (health = 0)
     */
    public void beginGame() {
        // create the maze
        Maze maze = new Maze();

        // get user input for adventurer's name
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter adventurer's name: ");
        String adventurerName = scanner.nextLine();
        // TODO: input error handling here?

        // randomly select a creature type
        Random random = new Random();
        String randomCreatureType = CREATURE_TYPES[random.nextInt(CREATURE_TYPES.length)]; // TODO: do we want this as a final variable?

        // create Creature and Adventurer objects
        Creature creature = new Creature(randomCreatureType); // use random creature from above
        Adventurer adventurer = new Adventurer(adventurerName); // name = from user input

        // TODO: randomly place each character in one of the rooms (and define/update neighbors?)

        // continue taking turns until one of the character's health = 0
        while (adventurer.getHealth() > 0 && creature.getHealth() > 0) {
            takeTurn();
        }
    }

    /**
     * takeTurn: Prints maze and turn number. Analyzes the room locations of the creature and adventurer
     * and calls the fight method if they are in the same room. Otherwise moves the adventurer to a random neighboring room
     * Increments turnCount
     */
    public void takeTurn() {

        turnCount++; // increment turn count
        printMaze();  // Print current state of the maze

        // if both players are in the same room, call fight method
        if (adventurerRoom.equals(creatureRoom)) { // TODO: might want to do this as Adventure.getRoom() and have room be a field in the Character class
            fight();
        } else { // if both players are not in the same room, move adventurer to a random neighboring room
            moveAdventurer();  // Move adventurer to a neighboring room
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

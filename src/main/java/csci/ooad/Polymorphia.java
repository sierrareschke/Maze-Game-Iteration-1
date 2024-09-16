package csci.ooad;

import java.util.Random;
import java.util.Scanner;

public class Polymorphia {

    private static final String[] CREATURE_TYPES = {"Ogre", "Goblin", "Troll", "Werewolf", "Vampire", "Gnome", "Zombie"};
    private int turnCount;
    private Creature creature;
    private Adventurer adventurer;
    private Room [] rooms;
    private Dice dice;
    private Character winner;


    /**
     * Constructor to create the maze with 4 rooms and set turn count to 0
     */
    public Polymorphia() {
        this.turnCount = 0;
        createRooms();
        dice = new Dice();
        winner = null;
    }

    /**
     * Accessor method to get the list of rooms in the maze
     * @return Room[] - array of rooms
     */
    public Room[] getRooms() {
        return rooms;
    }

    public Character getWinner() {
        return winner;
    }

    public Creature getCreature() {return creature;}

    public Adventurer getAdventurer() {return adventurer;}

    /**
     * createRooms: creates 4 rooms (NW, NE, SE, SW)
     */
    private void createRooms() {
        // Initialize the rooms array with 4 rooms (Northwest, Northeast, Southeast, Southwest)
        rooms = new Room[4];

        // Create and assign rooms to the respective positions
        rooms[0] = new Room("Northwest",0);  // Empty occupants for now
        rooms[1] = new Room("Northeast",1);
        rooms[2] = new Room("Southeast",2);
        rooms[3] = new Room("Southwest",3);
    }

    /**
     * beginGame: method to start the game (call maze constructor, ask for user input for adventurer name,
     * randomly selects type of creature, creates creature and adventure objects, and randomly places each
     * in one of the 4 rooms). Calls the takeTurn method until a player dies (health = 0)
     */
    public void playGame() {
        // Print that the game is starting
        System.out.println("Starting the game...");

        // get user input for adventurer's name
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter adventurer's name: ");
        String adventurerName = scanner.nextLine();

        randomlyDistributeCharacters(adventurerName);

        // Print the initial state of the game
        System.out.println("\nInitial game state:");
        printMaze();

        // begin taking turns
        // continue taking turns until one of the character's health = 0
        System.out.println("\nGame is ready. Adventurer and creature are placed in rooms.");
        System.out.println("Starting turns.");

        // call takeTurn while both creatures are alive
        while (creature.getHealth() > 0 && adventurer.getHealth() > 0) {
            takeTurn(adventurer, creature);
        }

        // getting here means one (or both) of the characters has died --> end game
        // nobody wins if both creature and adventurer's health is <= 0
        if (creature.getHealth() <= 0 && adventurer.getHealth() <= 0) {
            System.out.println("Both players die. Nobody wins. Game over.\n");
        }
        // otherwise, the creature wins if the adventurer's health is <= 0
        else if (adventurer.getHealth() <= 0) {
            winner = creature;
            System.out.println("Boo, the creature won. Game over.\n");
        }
        // if the creature's health is <= 0, the adventurer wins
        else {
            winner = adventurer;
            System.out.println("Adventurer " + adventurer.getName() + " has defeated the creature! Game over");
        }

        System.out.println("Exited beginGame method.\n");

    }

    public void randomlyDistributeCharacters (String adventurerName) {
        System.out.println("Adventurer's name is: " + adventurerName);

        // randomly select a creature type
        Random random = new Random();
        String randomCreatureType = CREATURE_TYPES[random.nextInt(CREATURE_TYPES.length)];
        System.out.println("Randomly selected creature: " + randomCreatureType);

        // create Creature and Adventurer objects
        creature = new Creature(randomCreatureType);
        adventurer = new Adventurer(adventurerName);
        System.out.println("Created adventurer: " + adventurer);
        System.out.println("Created creature: " + creature);

        // Randomly place each character in one of the rooms (they can be placed in the same room)
        int randomRoomIndexForAdventurer = random.nextInt(rooms.length); // Random room for adventurer
        int randomRoomIndexForCreature = random.nextInt(rooms.length);   // Random room for creature

        // Add adventure and creature to respective random rooms
        rooms[randomRoomIndexForAdventurer].addOccupant(adventurer);
        rooms[randomRoomIndexForCreature].addOccupant(creature);

        // Report placement of adventurer and creature
        System.out.println("Adventurer placed in: " + rooms[randomRoomIndexForAdventurer].getName());
        System.out.println("Creature placed in: " + rooms[randomRoomIndexForCreature].getName());
    }

    /**
     * takeTurn: Prints maze and turn number. Analyzes the room locations of the creature and adventurer
     * and calls the fight method if they are in the same room. Otherwise, moves the adventurer to a random neighboring room
     * Increments turnCount
     */
    public void takeTurn(Adventurer adventurer, Creature creature) {

        turnCount++; // increment turn count
        printMaze();  // Print current state of the maze

        // if both players are in the same room, call fight method
        if (areBothInSameRoom(adventurer, creature)) {
            fight(adventurer, creature); // players fight
            // both characters lose 0.5 points, regardless of fight outcome
            System.out.println("Both players lose 0.5 health after fight.");
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
    public boolean areBothInSameRoom(Adventurer adventurer, Creature creature) {
        Room adventurerRoom = null;
        Room creatureRoom = null;

        // Loop through each room to find where the adventurer and creature are
        for (Room room : rooms) {
            // Check if the adventurer is in this room
            for (Character occupant : room.getOccupants()) {
                if (occupant.equals(adventurer)) {
                    adventurerRoom = room;
                }
                // Check if the creature is in this room
                if (occupant.equals(creature)) {
                    creatureRoom = room;
                }
            }
        }

        // Return true if both the adventurer and the creature are in the same room
        return adventurerRoom != null && adventurerRoom.equals(creatureRoom);
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

        String mazeType = ""; // Initialize an empty string for the maze type

        if (this instanceof Maze) {
            mazeType = "MAZE";
        } else if (this instanceof Cave) {
            mazeType = "CAVE";
        } else if (this instanceof Polymorphia) {
            mazeType = ""; // Do not print anything for Polymorphia
        }

        System.out.println("Polymorphia " + mazeType + ": turn " + turnCount);


        for (Room room : rooms) {
            System.out.println(room.toString()); // print out each room and its occupants
        }
    }


    /**
     * moveAdventurer: moves the adventurer to a random neighboring room
     * neighboring room = roomIndex +- 1
     * ensure index is in range of rooms array
     */
    public void moveAdventurer(Adventurer adventurer) {
        // Get the current room index of the adventurer
        Room roomOfAdventurer = getRoomOfCharacter(adventurer);
        int currentRoomIndex = roomOfAdventurer.getIndex();

        // Randomly decide whether to move to the next room (index + 1) or previous room (index - 1)
        Random random = new Random();
        int direction = random.nextBoolean() ? 1 : -1; // 1 for next, -1 for previous

        // Calculate the new room index using modulus to wrap around
        int newRoomIndex = (currentRoomIndex + direction + rooms.length) % rooms.length;

        // remove the adventurer from the current room, update index, add to new room
        roomOfAdventurer.removeAdventurer(adventurer);
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
        int adventurerRoll = dice.rollDice();
        int creatureRoll = dice.rollDice();

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



    /**
     * Method to find which room a character is in
     * @param character to find which room in
     * @return Room that the character is in
     */
    public Room getRoomOfCharacter(Character character) {
        for (Room room : rooms) {
            for (Character occupant : room.getOccupants()) {
                if (occupant.equals(character)) {
                    return room; // character is an occupant in this room
                }
            }
        }
        return null; // Character not found in any room
    }

    /**
     * Method to clear all rooms of their occupants
     */
    public void clearRooms() {
        for (Room room : rooms) {
            room.emptyRoom();
        }
    }

}

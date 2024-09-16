package csci.ooad;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class PolymorphiaTest {

    private InputStream originalIn;
    private Polymorphia testPolymorphia;

    /*
    * Before each test, store original System.in and initialize testPolymorphia
    * */
    @BeforeEach
    public void setup() {
        this.originalIn = System.in;
        this.testPolymorphia = new Polymorphia();
    }

    /*
    * Test to see if polymorphia initialization properly creates rooms and has no winner
    * */
    @Test
    public void testPolymorphiaInitialization() {
        assertNotNull(testPolymorphia.getRooms(), "Rooms should be initialized.");
        assertEquals(4, testPolymorphia.getRooms().length, "Polymorphia should have 4 rooms.");
        assertNull(testPolymorphia.getWinner(), "Winner should be null at the beginning.");
    }


    /* Test to see if playGame() properly handles and reports the result of the game */
    @Test
    public void testPlayGame() {
        // Simulate input for the adventurer's name
        String simulatedInput = "Test Adventurer\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        try{
            testPolymorphia.playGame();

            // Retrieve the adventurer and creature
            Adventurer adventurer = testPolymorphia.getAdventurer();
            Creature creature = testPolymorphia.getCreature();

            assertNotNull(adventurer, "Adventurer should not be null.");
            assertNotNull(creature, "Creature should not be null.");

            // Check the game result (whether adventurer, creature won, or no winner)
            Character winner = testPolymorphia.getWinner();

            if (winner == null) {
                // No winner: both the adventurer and the creature should have health <= 0
                assertTrue(adventurer.getHealth() <= 0, "Adventurer's health should be less than or equal to 0 if no winner.");
                assertTrue(creature.getHealth() <= 0, "Creature's health should be less than or equal to 0 if no winner.");
            } else if (winner instanceof Adventurer) {
                // Adventurer wins: adventurer's health should be > 0 and creature's health <= 0
                assertTrue(adventurer.getHealth() > 0, "Adventurer should have positive health if they won.");
                assertTrue(creature.getHealth() <= 0, "Creature's health should be less than or equal to 0 if adventurer won.");
            } else if (winner instanceof Creature) {
                // Creature wins: creature's health should be > 0 and adventurer's health <= 0
                assertTrue(creature.getHealth() > 0, "Creature should have positive health if they won.");
                assertTrue(adventurer.getHealth() <= 0, "Adventurer's health should be less than or equal to 0 if creature won.");
            }
        } finally {
            System.setIn(originalIn);
        }

    }

    /*
    * Test to see if randomlyDistributeCharacters() distributes characters randomly
    * */
    @Test
    public void testRandomlyDistributeCharacters() {

        try {
            // Initialize counters for room distributions
            Map<String, Integer> adventurerRoomDistribution = new HashMap<>();
            Map<String, Integer> creatureRoomDistribution = new HashMap<>();

            // Initialize the room names in the maps
            String[] roomNames = {"Northwest", "Northeast", "Southeast", "Southwest"};
            for (String room : roomNames) {
                adventurerRoomDistribution.put(room, 0);
                creatureRoomDistribution.put(room, 0);
            }

            // 100 trials: randomly distribute characters and store result in hashmap
            for (int i = 0; i < 100; i++) {
                testPolymorphia.randomlyDistributeCharacters("Test Adventurer");  // Distribute the characters randomly

                // Get the rooms and check the distribution
                Room[] rooms = testPolymorphia.getRooms();

                for (Room room : rooms) {
                    for (Character occupant : room.getOccupants()) {
                        if (occupant instanceof Adventurer) {
                            adventurerRoomDistribution.put(room.getName(), adventurerRoomDistribution.get(room.getName()) + 1);
                        }
                        if (occupant instanceof Creature) {
                            creatureRoomDistribution.put(room.getName(), creatureRoomDistribution.get(room.getName()) + 1);
                        }
                    }
                }

                testPolymorphia.clearRooms(); // Clear rooms for next iteration
            }

            // Ensure that each room got at least one adventurer and one creature
            for (String room : roomNames) {
                assertTrue(adventurerRoomDistribution.get(room) > 0, "Adventurer should be placed in " + room + " at least once.");
                assertTrue(creatureRoomDistribution.get(room) > 0, "Creature should be placed in " + room + " at least once.");
            }

            // Print the distribution for manual verification
            System.out.println("Adventurer Room Distribution: " + adventurerRoomDistribution);
            System.out.println("Creature Room Distribution: " + creatureRoomDistribution);
        } finally {
            System.setIn(originalIn); // reset System.in
        }
    }


    /*
    * Test to see if adventureMoves() transfers adventure to a neighboring room
    * */
    @Test
    public void testAdventurerMoves() {
        // Distribute characters in the polymorphia
        testPolymorphia.randomlyDistributeCharacters("Test Adventurer");

        // Get the adventurer from the polymorphia
        Adventurer adventurer = testPolymorphia.getAdventurer();
        assertNotNull(adventurer, "Adventurer should be in the polymorphia.");

        // Get the original room of the adventurer
        Room originalRoom = testPolymorphia.getRoomOfCharacter(adventurer);
        assertNotNull(originalRoom, "Adventurer should have a room.");

        int originalRoomIndex = originalRoom.getIndex();  // Get the original room index

        // Move the adventurer to a neighboring room
        testPolymorphia.moveAdventurer(adventurer);

        // Get the new room of the adventurer
        Room newRoom = testPolymorphia.getRoomOfCharacter(adventurer);
        assertNotNull(newRoom, "Adventurer should have moved to a new room.");
        assertNotEquals(originalRoom, newRoom, "Adventurer should have moved to a different room.");

        int newRoomIndex = newRoom.getIndex();  // Get the new room index

        // Calculate the valid neighboring room indices (circular wrap)
        int nextRoomIndex = (originalRoomIndex + 1) % testPolymorphia.getRooms().length;
        int previousRoomIndex = (originalRoomIndex - 1 + testPolymorphia.getRooms().length) % testPolymorphia.getRooms().length;

        // Assert that the new room index is either the next or previous room
        assertTrue(newRoomIndex == nextRoomIndex || newRoomIndex == previousRoomIndex,
                "Adventurer should move to a neighboring room (either previous or next).");

        System.setIn(originalIn);  // Reset System.in
    }

    @Test
    public void testFight() {
        // Initialize counters for fight outcomes
        int adventurerWins = 0;
        int creatureWins = 0;
        int ties = 0;

        // Run the test 100 times
        for (int i = 0; i < 100; i++) {
            // Initialize a new adventurer and creature
            Adventurer adventurer = new Adventurer("Test Adventurer");
            Creature creature = new Creature("Test Zombie");

            // Place adventurer and creature in the same room
            Room room = testPolymorphia.getRooms()[0];  // Put both in the same room for testing
            room.addOccupant(adventurer);
            room.addOccupant(creature);

            // Capture initial health values
            double initialAdventurerHealth = adventurer.getHealth();
            double initialCreatureHealth = creature.getHealth();

            // Call fight (this will simulate a fight since they're in the same room)
            testPolymorphia.fight(adventurer, creature);

            double currentAdventurerHealth = adventurer.getHealth();
            double currentCreatureHealth = creature.getHealth();

            // Check fight outcome
            if (currentAdventurerHealth == initialAdventurerHealth && currentCreatureHealth == initialCreatureHealth) {
                // Tie (no health lost)
                ties++;
            } else if (currentAdventurerHealth < initialAdventurerHealth) {
                // Creature won (adventurer lost health)
                creatureWins++;
            } else if (currentCreatureHealth < initialCreatureHealth) {
                // Adventurer won (creature lost health)
                adventurerWins++;
            }

            // Reset the room for the next iteration
            testPolymorphia.clearRooms();
        }

        // Ensure that each outcome occurred at least once
        assertTrue(adventurerWins > 0, "Adventurer should win at least once.");
        assertTrue(creatureWins > 0, "Creature should win at least once.");
        assertTrue(ties > 0, "There should be at least one tie.");

        // Print the distribution of outcomes
        System.out.println("Fight outcomes after 100 trials:");
        System.out.println("Adventurer wins: " + adventurerWins);
        System.out.println("Creature wins: " + creatureWins);
        System.out.println("Ties: " + ties);
    }



    // testTakeTurn : Tests that if characters in same room, fight. If not, move adventurer called
    @Test
    public void testTakeTurn() {
        // Initialize adventurer and creature
        Adventurer adventurer = new Adventurer("Test Adventurer");
        Creature creature = new Creature("Test Zombie");

        // Test scenario where adventurer and creature are in the same room (should trigger a fight)
        Room room = testPolymorphia.getRooms()[0];  // Place both in the same room
        room.addOccupant(adventurer);
        room.addOccupant(creature);

        // Call takeTurn (this should trigger a fight)
        testPolymorphia.takeTurn(adventurer, creature);

        // Both should lose at least 0.5 health due to the fight
        assertTrue(adventurer.getHealth() < 5.0, "Adventurer should lose at least 0.5 health.");
        assertTrue(creature.getHealth() < 5.0, "Creature should lose at least 0.5 health.");

        // Test scenario where adventurer and creature are in different rooms (should move the adventurer)
        room.emptyRoom();
        Room adventurerRoom = testPolymorphia.getRooms()[0];
        Room createureRoom = testPolymorphia.getRooms()[1];
        adventurerRoom.addOccupant(adventurer);
        createureRoom.addOccupant(creature);

        // Call takeTurn (this should trigger the adventurer to move)
        testPolymorphia.takeTurn(adventurer, creature);

        // Verify that the adventurer has moved from the original room
        Room adventurerNewRoom = testPolymorphia.getRoomOfCharacter(adventurer);
        assertNotEquals(room, adventurerNewRoom, "Adventurer should have moved to a neighboring room.");
    }




}

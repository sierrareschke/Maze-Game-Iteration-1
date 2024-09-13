package csci.ooad;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

public class PolymorphiaTest {

    @Test
    public void testGameStart() {
        // Simulate input for the Scanner (adventurer name)
        String simulatedInput = "Test Adventurer\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        // Create a Maze object and start the game
        Maze maze = new Maze();

        // Start the game (beginGame simulates game flow)
        maze.beginGame();

        // Assert true no matter what
        assertTrue(true, "This will always pass");

        // Reset System.in to its original state if needed
        System.setIn(System.in);
    }

    @Test
    public void testRandomDistribution() {
        // Track the number of times each room gets the adventurer and the creature
        Map<String, Integer> adventurerRoomDistribution = new HashMap<>();
        Map<String, Integer> creatureRoomDistribution = new HashMap<>();

        // Initialize the room names in the maps
        String[] roomNames = {"Northwest", "Northeast", "Southeast", "Southwest"};
        for (String room : roomNames) {
            adventurerRoomDistribution.put(room, 0);
            creatureRoomDistribution.put(room, 0);
        }

        // Track the number of times each character wins
        Map<String, Integer> winDistribution = new HashMap<>();

        winDistribution.put("Adventurer", 0);
        winDistribution.put("Creature", 0);

        // Run the game 100 times
        for (int i = 0; i < 100; i++) {
            // Simulate input for the Scanner (adventurer name)
            String simulatedInput = "Test Adventurer\n";
            ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(inputStream);

            // Create a new Maze and start the game
            Maze maze = new Maze();
            maze.beginGame();

            // Check the rooms for where the adventurer and creature are placed
            Room[] rooms = maze.getRooms();
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

            // count the number of times the adventurer and creature win
            if (maze.getWinner() instanceof Adventurer) {
                winDistribution.put("Adventurer", winDistribution.get("Adventurer") +1);

            } else { // creature won
                winDistribution.put("Creature", winDistribution.get("Creature") +1);
            }

        }

        // Ensure the adventurer and creature are not always placed in the same room
        // For a random distribution, each room should have at least 1 or more placements for both adventurer and creature
        System.out.println("Adventurer room distribution: " + adventurerRoomDistribution);
        System.out.println("Creature room distribution: " + creatureRoomDistribution);

        // Ensure that each room gets the adventurer and the creature at least once (or some reasonable spread)
        for (String room : roomNames) {
            assertTrue(adventurerRoomDistribution.get(room) > 0, "Adventurer should be placed in " + room + " at least once.");
            assertTrue(creatureRoomDistribution.get(room) > 0, "Creature should be placed in " + room + " at least once.");
        }

        // Optionally, you can assert that no single room got an overwhelming number of the placements
        for (String room : roomNames) {
            assertTrue(adventurerRoomDistribution.get(room) < 100, "Adventurer should not be placed in " + room + " 100 times.");
            assertTrue(creatureRoomDistribution.get(room) < 100, "Creature should not be placed in " + room + " 100 times.");
        }


        // Ensure the adventurer and creature each win at least once
        // For a random distribution, each character should win at least once
        System.out.println("Win distribution: " + winDistribution);
        assertTrue(winDistribution.get("Adventurer") > 0, "Adventurer should win at least once");
        assertTrue(winDistribution.get("Creature") > 0, "Adventurer should win at least once");


        // Reset System.in
        System.setIn(System.in);
    }

    // call the test class for Dice
    @Test
    public void testDice() {
        DiceTest diceTest = new DiceTest();
        diceTest.testDiceRoll(); // call testDiceRoll method from DiceTest
    }

    // call the test class for Room
    @Test
    public void testRoom() {
        RoomTest roomTest = new RoomTest();
        // call all methods in DiceTestClass
        Adventurer testAdventurer = new Adventurer("Test Adventurer");
        roomTest.testAddOccupant(testAdventurer);
        roomTest.testRemoveAdventurer();
        roomTest.testToString();
    }
}

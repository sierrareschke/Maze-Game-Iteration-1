package csci.ooad;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RoomTest {

    private Room room;
    private Adventurer adventurer;
    private Creature creature;

    // create room, adventurer, creature objects before each test
    @BeforeEach
    public void setUp() {
        // Initialize a new Room and test characters before each test
        room = new Room("TestRoom");
        adventurer = new Adventurer("TestAdventurer");
        creature = new Creature("TestCreature");
    }

    // Method to test the Room constructor by checking the name and index
    @Test
    public void testConstructor() {
        Room room = new Room("TestRoom", 1);
        assertEquals("TestRoom", room.getName(), "Room name should be TestRoom");
        assertEquals(1, room.getIndex(), "Room index should be 1");
    }


    // Method to test if occupants are successfully added to Rooms
    @Test
    public void testAddOccupant() {
        Room room = new Room();
        room.addOccupant(adventurer);
        assertEquals(1, room.getOccupants().length, "Room should have 1 occupant after adding an adventurer.");

        room.addOccupant(creature);
        assertEquals(2, room.getOccupants().length, "Room should have 2 occupants after adding a creature.");
    }

    // Method to test if occupants are correctly removed from a room
    @Test
    public void testRemoveAdventurer() {
        // add adventurer and creature
        room.addOccupant(adventurer);
        room.addOccupant(creature);

        // Remove the adventurer
        Adventurer removedAdventurer = room.removeAdventurer(adventurer);
        assertNotNull(removedAdventurer, "Removed adventurer should not be null.");
        assertEquals(1, room.getOccupants().length, "Room should have 1 occupant after removing the adventurer.");

        // Attempt to remove the same adventurer again, should return null
        Adventurer removedAgain = room.removeAdventurer(adventurer);
        assertNull(removedAgain, "Removing the same adventurer again should return null.");
    }


    // Method to test the toString method for Room, ensuring correct output
    @Test
    public void testToString() {
        assertEquals("TestRoom:\nNo occupants are here.", room.toString(), "Room should indicate that it is empty.");

        room.addOccupant(adventurer);
        assertTrue(room.toString().contains("Adventurer TestAdventurer(health: 5.0) is here."),
                "Room should contain the adventurer's information.");

        room.addOccupant(creature);
        assertTrue(room.toString().contains("Creature TestCreature(health: 5.0) is here."),
                "Room should contain the creature's information.");
    }
}

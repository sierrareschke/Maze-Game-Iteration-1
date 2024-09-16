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
        adventurer = new Adventurer("TestAdventurer");
        creature = new Creature("TestCreature");
    }

    // Method to test the Room constructor by checking the name and index
    @Test
    public void testConstructorOne() {
        room = new Room("TestRoom", 1);
        assertEquals("TestRoom", room.getName(), "Room name should be TestRoom");
        assertEquals(1, room.getIndex(), "Room index should be 1");
    }


    @Test
    public void testConstructorTwo() {
        room = new Room("TestRoom");
        assertEquals("TestRoom", room.getName(), "Room name should be TestRoom");
        assertEquals(-1, room.getIndex(), "Room index should be -1");
    }

    @Test
    public void testConstructorThree() {
        room = new Room();
        assertEquals("", room.getName(), "Room name should be TestRoom");
        assertEquals(-1, room.getIndex(), "Room index should be -1");
    }


    // Method to test if occupants are successfully added to Rooms
    @Test
    public void testAddOccupant() {
        room = new Room();
        room.addOccupant(adventurer);
        assertEquals(1, room.getOccupants().size(), "Room should have 1 occupant after adding an adventurer.");

        room.addOccupant(creature);
        assertEquals(2, room.getOccupants().size(), "Room should have 2 occupants after adding a creature.");
    }

    // Method to test if occupants are correctly removed from a room
    @Test
    public void testRemoveAdventurer() {
        room = new Room();

        // add adventurer and creature
        room.addOccupant(adventurer);
        room.addOccupant(creature);

        // Remove the adventurer
        Adventurer removedAdventurer = room.removeAdventurer(adventurer);
        assertNotNull(removedAdventurer, "Removed adventurer should not be null.");
        assertEquals(1, room.getOccupants().size(), "Room should have 1 occupant after removing the adventurer.");

        // Attempt to remove the same adventurer again, should return null
        Adventurer removedAgain = room.removeAdventurer(adventurer);
        assertNull(removedAgain, "Removing the same adventurer again should return null.");
    }




    // Method to test the toString method for Room, ensuring correct output
    @Test
    public void testToString() {
        // Test empty room case
        Room emptyRoom = new Room("Northwest");
        String expectedEmptyRoomOutput = "Northwest:\nNo occupants are here.";
        assertEquals(expectedEmptyRoomOutput, emptyRoom.toString(), "Empty room description should match.");
    }

    @Test void testToStringOneOccupant() {
        // Test room with occupants case
        Room occupiedRoom = new Room("Southeast");
        Adventurer adventurer = new Adventurer("Test Adventurer");
        occupiedRoom.addOccupant(adventurer);

        String expectedOccupiedRoomOutput = "Southeast:\nAdventurer Test Adventurer(health: 5.0) is here. ";
        assertEquals(expectedOccupiedRoomOutput, occupiedRoom.toString(), "Occupied room description should match.");
    }

    @Test
    public void testToStringMultipleOccupants() {
        Room room = new Room("East");
        Adventurer adventurer = new Adventurer("Test Adventurer");
        Creature creature = new Creature("Test Creature");
        room.addOccupant(adventurer);
        room.addOccupant(creature);

        String expectedOutput = "East:\nAdventurer Test Adventurer(health: 5.0) is here. Creature Test Creature(health: 5.0) is here. ";
        assertEquals(expectedOutput, room.toString(), "Room description with multiple occupants should match.");
    }



    @Test
    public void testEmptyRoom() {
        Room room = new Room("Test Room");
        Adventurer adventurer = new Adventurer("Test Adventurer");
        Creature creature = new Creature("Test Creature");

        room.addOccupant(adventurer);
        room.addOccupant(creature);

        // Empty the room
        room.emptyRoom();
        assertTrue(room.getOccupants().isEmpty());
    }

}

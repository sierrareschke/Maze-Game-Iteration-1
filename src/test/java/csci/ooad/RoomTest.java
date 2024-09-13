package csci.ooad;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RoomTest {

    // fields
    private Room room;
    private Adventurer adventurer;
    private Creature creature;

    // create room, adventurer, creature objects before each test
    @BeforeEach
    public void setUp() {
        // Initialize a new Room and test characters before each test
        room = new Room("Test Room");
        adventurer = new Adventurer("Test Adventurer");
        creature = new Creature("Test Creature");
    }

    // test if occupants are correctly added to the room array
    @Test
    public void testAddOccupant(Adventurer testAdventurer) {
        room.addOccupant(adventurer);
        assertEquals(1, room.getOccupants().length, "Room should have 1 occupant after adding an adventurer.");

        room.addOccupant(creature);
        assertEquals(2, room.getOccupants().length, "Room should have 2 occupants after adding a creature.");
    }

    // test if occupants are correctly removed from a room
    @Test
    public void testRemoveAdventurer() {
        room.addOccupant(adventurer);
        room.addOccupant(creature);

        // Remove the adventurer
        Adventurer removedAdventurer = room.removeAdventurer(adventurer);
        assertNotNull(removedAdventurer, "Removed adventurer should not be null.");
        assertEquals(1, room.getOccupants().length, "Room should have 1 occupant after removing the adventurer.");

        // Attempt to remove the same adventurer again
        Adventurer removedAgain = room.removeAdventurer(adventurer);
        assertNull(removedAgain, "Removing the same adventurer again should return null.");
    }

    // TODO
    @Test
    public void testToString() {
        assertEquals("Test Room:\nNo occupants are here.", room.toString(), "Room should indicate that it is empty.");

        room.addOccupant(adventurer);
        assertTrue(room.toString().contains("Adventurer Test Adventurer(health: 5.0) is here."),
                "Room should contain the adventurer's information.");

        room.addOccupant(creature);
        assertTrue(room.toString().contains("Creature Test Creature(health: 5.0) is here."),
                "Room should contain the creature's information.");
    }
}

package csci.ooad;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

public class PolymorphiaTest {

    private Maze maze;
    private Room room;
    private Adventurer adventurer;
    private Creature creature;

    // create room, adventurer, creature objects before each test
    @BeforeEach
    public void setUp() {
        // Initialize a new Room, test characters, and Maze before each test
        room = new Room("TestRoom");
        adventurer = new Adventurer("TestAdventurer");
        creature = new Creature("TestCreature");
        maze = new Maze();

    }

//    @Test
//    public void setUp() {
//        // Initialize the Maze
//        maze = new Maze();
//    }


    /**
     *  test if areBothInSameRoom correctly returns true when the adventurer and creature
     *  are in the same room and false if not
     */
    @Test
    public void testAreBothInSameRoom() {
        // create room and add both characters to same room
        int sameRoomIndex = 0;
//        room.addOccupant(adventurer);
//        room.addOccupant(creature);

       // assertEquals(true, maze.areBothInSameRoom(adventurer, creature), "Adventurer and Creature are in same room");

    }

}

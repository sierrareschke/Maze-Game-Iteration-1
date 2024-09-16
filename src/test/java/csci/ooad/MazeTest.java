package csci.ooad;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MazeTest {

    @Test
    public void testMazeCreation() {
        Maze maze = new Maze();
        assertNotNull(maze, "Maze instance should be created.");
    }
}

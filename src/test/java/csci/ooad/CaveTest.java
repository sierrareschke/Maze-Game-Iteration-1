package csci.ooad;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CaveTest {

    @Test
    public void testCaveCreation() {
        Cave cave = new Cave();
        assertNotNull(cave, "Cave instance should be created.");
    }
}

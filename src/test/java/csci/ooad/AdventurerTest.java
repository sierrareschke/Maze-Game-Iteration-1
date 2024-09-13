package csci.ooad;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AdventurerTest {

    private Adventurer adventurer;

    // This method will run before each test to initialize the adventurer object
    @BeforeEach
    public void setUp() {
        adventurer = new Adventurer("TestAdventurer");
    }

    // Test the initial health of the adventurer
    @Test
    public void testInitialHealth() {
        assertEquals(5.0, adventurer.getHealth(), "Initial adventurer health should be 5.0");
    }

    // Test the name access method for adventurer
    @Test
    public void testGetName() {
        assertEquals("TestAdventurer", adventurer.getName(), "Adventurer name should be TestAdventurer");
    }
}

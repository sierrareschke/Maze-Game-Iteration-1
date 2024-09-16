package csci.ooad;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CreatureTest {

    private Creature creature;

    // This method will run before each test to initialize the creature object
    @BeforeEach
    public void setUp() {
        creature = new Creature("TestCreature");
    }

    // Test the initial health of the creature
    @Test
    public void testInitialHealth() {
        assertEquals(5.0, creature.getHealth(), "Initial creature health should be 5.0");
    }

    // Test the name access method for creature
    @Test
    public void testGetName() {
        assertEquals("TestCreature", creature.getName(), "Creature name should be TestCreature");
    }
}

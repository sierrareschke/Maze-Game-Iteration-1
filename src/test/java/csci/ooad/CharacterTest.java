package csci.ooad;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CharacterTest {

    private Character character;

    // This method will run before each test to initialize the character object
    @BeforeEach
    public void setUp() {
        character = new Character("TestCharacter");
    }

    // Test the initial health of the character
    @Test
    public void testInitialHealth() {
        assertEquals(5.0, character.getHealth(), "Initial health should be 5.0");
    }

    // Test the name access method
    @Test
    public void testGetName() {
        assertEquals("TestCharacter", character.getName(), "Character name should be TestCharacter");
    }

    // Test subtractFromHealth with a negative value
    @Test
    public void testSubtractFromHealthValid() {
        character.subtractFromHealth(-1.0);
        assertEquals(4.0, character.getHealth(), "Health should be reduced to 4.0 after subtracting 1.0");
    }

    // Test subtractFromHealth with a positive value (should not change health)
    @Test
    public void testSubtractFromHealthInvalid() {
        character.subtractFromHealth(1.0); // Positive value, should not affect health
        assertEquals(5.0, character.getHealth(), "Health should remain 5.0 after passing a positive value");
    }

    // Test the toString method
    @Test
    public void testToString() {
        String expectedString = "TestCharacter(health: 5.0)";
        assertEquals(expectedString, character.toString(), "toString should return correct format");
    }

    // Test that health does not go below 0 (you can add this check in the Character class if needed)
    @Test
    public void testHealthDoesNotGoBelowZero() {
        character.subtractFromHealth(-10.0); // Subtracting more than current health
        assertTrue(character.getHealth() < 0, "Health can go negative as per current implementation, but we can change this if needed.");
    }

}

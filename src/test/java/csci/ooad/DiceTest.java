package csci.ooad;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

public class DiceTest {

    @Test
    public void testDiceRoll() {
        // create Dice object
        Dice dice = new Dice();
        // create HashMap to keep track of rolls
        Map<Integer, Integer> rollCounts = new HashMap<>();

        // initialize the map to track counts for numbers 1 to 6
        for (int i = 1; i <= 6; i++) {
            rollCounts.put(i, 0);
        }

        // roll the dice 1000 times
        int numberOfRolls = 1000;
        for (int i = 0; i < numberOfRolls; i++) {
            int rollResult = dice.rollDice();
            // update the HashMap to increment the count for the rolled integer
            rollCounts.put(rollResult, rollCounts.get(rollResult) + 1);
        }

        // check that all numbers 1 to 6 are present and rolled at least a minimum number of times
        for (int i = 1; i <= 6; i++) {
            assertTrue(rollCounts.get(i) > 0, "Number " + i + " should have been rolled at least once.");
        }

    }
}

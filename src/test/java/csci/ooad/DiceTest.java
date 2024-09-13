package csci.ooad;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

public class DiceTest {

    /**
     * Tester method to test that the rollDice method returns only integers 1-6
     * and that each number is rolled at least once and with a random distribution
     */
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
        int NUMBER_OF_ROLLS = 1000;
        for (int i = 0; i < NUMBER_OF_ROLLS; i++) {
            int rollResult = dice.rollDice();
            // update the HashMap to increment the count for the rolled integer
            rollCounts.put(rollResult, rollCounts.get(rollResult) + 1);
        }

        double EXPECTED_AVERAGE = NUMBER_OF_ROLLS / 6.0;
        double TOLERANCE = EXPECTED_AVERAGE * 0.10; // 10% tolerance

        // Check that all numbers 1 to 6 are rolled within the acceptable range
        int NUM_SIDES = 6;
        for (int i = 1; i <= NUM_SIDES; i++) {
            int count = rollCounts.get(i);
            assertTrue(Math.abs(count - EXPECTED_AVERAGE) <= TOLERANCE,
                    "Number " + i + " was rolled " + count + " times, which is outside the acceptable range.");
        }
    }
}

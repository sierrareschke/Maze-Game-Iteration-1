package csci.ooad;
import java.util.Random;

public class Dice {

    private Random random;

    // Constructor to initialize Random object
    public Dice() {
        random = new Random();
    }


    /**
     * Method to roll the dice (return a number between 1 and 6)
     * @return - random int between 1 and 6
     */
    public int rollDice() {
        return random.nextInt(6) + 1; // Generates a number between 1 and 6
    }

}

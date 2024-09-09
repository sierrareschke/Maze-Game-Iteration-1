public class Character {

    // fields
    private String name;
    private int health;
    // TODO: added a field to have each character store their current room
    private


    // constructor
    Character(String name) {
        this.name = name;
        this.health = 5; // initial health set to 5
    }

    // methods

    /**
     * Method to get current health
     *
     * @return health - the Character's current health
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Decrements the Character's health by adding a negative number
     *
     * @param numToSubtract
     */
    public void subtractFromHealth(int numToSubtract) {
        // check that numToSubtract is negative
        if (numToSubtract < 0) {
            this.health += numToSubtract; // adding negative number subtracts health
        } else {
            System.out.println("Value must be negative to subtract from health.");
        }
    }

    /**
     * Method to roll one die and return the value
     *
     * @return roll - result of single die rolled
     */
    public int rollDie() {
        int roll = (int) (Math.random() * 6) + 1;
        return roll; // return roll value
    }

    /**
     * toString method to return Character's name(health: health)
     *
     * @return string of Character's name and health
     */
    @Override
    public String toString() {
        return name + "(health: " + health + ")";
    }


}

package csci.ooad;


public class Character {

    private String name;
    private double health;


    /**
     * Constructor for Character objects. Sets name and initial health (5.0)
     * @param name - name of Character
     */
    Character(String name) {
        this.name = name;
        this.health = 5.0; // initial health set to 5.0
    }

    /**
     * returns the name of the Character
     * @return - name
     */
    public String getName() {return name;}

    /**
     * Method to get current health
     * @return health - the Character's current health
     */
    public double getHealth() {
        return this.health;
    }

    /**
     * Decrements the Character's health by adding a negative number
     * @param numToSubtract - a negative value passed to decrease the character's health by
     */
    public void subtractFromHealth(double numToSubtract) {
        // check that numToSubtract is negative
        if (numToSubtract < 0) {
            this.health += numToSubtract; // adding negative number subtracts health
        } else {
            System.out.println("Value must be negative to subtract from health.");
        }
    }

    /**
     * toString method to return Character's name(health: health)
     * @return string of Character's name and health
     * Ex. testAdventurer(health: 5.0)
     */
    @Override
    public String toString() {
        return name + "(health: " + health + ")";
    }

}

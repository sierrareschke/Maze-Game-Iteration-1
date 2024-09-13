package csci.ooad;

public class Character {

    // fields
    private String name;
    private double health;
    private int roomIndex;
    private boolean wonGame;

    // constructor
    Character(String name) {
        this.name = name;
        this.health = 5.0; // initial health set to 5.0
        this.wonGame = false;
    }

    // methods

    /**
     * Method to get current health
     *
     * @return health - the Character's current health
     */
    public double getHealth() {
        return this.health;
    }

    /**
     * Decrements the Character's health by adding a negative number
     *
     * @param numToSubtract
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

    /**
     * Method to get the current room index
     * @return roomIndex - the index of the room where the character is located
     */
    public int getRoomIndex() {
        return this.roomIndex;
    }

    /**
     * Method to update the room index when the character moves to a new room
     * @param newRoomIndex - the new index of the room
     */
    public void updateRoomIndex(int newRoomIndex) {
        this.roomIndex = newRoomIndex;
    }

    /**
     * Method to set player as having won the game
     * @return true
     */
    public boolean setWin() {
        this.wonGame = true;
        return this.wonGame;
    }

}

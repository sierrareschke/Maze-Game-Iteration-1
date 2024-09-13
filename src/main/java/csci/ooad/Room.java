package csci.ooad;

public class Room {

    private String name;
    private Character [] occupants;
    private int index;


    /**
     * Default constructor, initializes name, occupants, and index to default values
     */
    public Room() {
        this.name = "";
        this.occupants = new Character[0]; // Initializes with an empty array
        this.index = -1;//
    }


    /**
     * Room constructor with a room name. Initializes the room name to name and
     * occupants and room index to default values
     * @param name - name of Room
     */
    public Room(String name){
        this.name = name;
        this.occupants = new Character[0]; // Initializes with an empty a
        this.index = -1;
    }

    /**
     * Room constructor with name and index to initialize, also initializes an empty array for occupants
     * @param name - name of Room
     * @param index - index of Room
     */
    public Room(String name,int index) {
        this.name = name;
        this.occupants = new Character[0]; // Initialize with an empty array
        this.index = index;
    }

    /**
     * Accessor method to get the name of the Room
     * @return name of Room
     */
    public String getName() {
        return name;
    }

    /**
     * Accessor method to get the Room's index
     * @return index of Room
     */
    public int getIndex() {return index;}


    /**
     * Getter method for Room's occupants
     * @return array of the Characters in the room
     */
    public Character[] getOccupants() {
        return occupants;
    }


    /**
     * Method to add a Character to a room as an occupant
     * @param occupant - Character object to add to the room
     */
    public void addOccupant(Character occupant) {
        // Create a new array with one more space than the current occupants array
        Character[] newOccupants = new Character[occupants.length + 1];

        // Copy existing occupants to the new array
        for (int i = 0; i < occupants.length; i++) {
            newOccupants[i] = occupants[i];
        }

        // Add the new occupant to the last position of the new array
        newOccupants[occupants.length] = occupant;

        // Update the occupants array
        this.occupants = newOccupants;
    }


    /**
     * Method to remove an Adventurer occupant from a Room
     * @param occupant - Adventurer to remove from Room
     * @return the Adventurer that was removed
     */
    public Adventurer removeAdventurer(Adventurer occupant) {
        // Check if the occupant is in the room by checking list of occupants
        boolean found = false;
        for (Character character : occupants) {
            if (character instanceof Adventurer && character.equals(occupant)) {
                found = true;
                break;
            }
        }

        if (!found) {
            return null; // Adventurer not found
        }

        // Create a new array with one less space than the current occupants array
        Character[] newOccupants = new Character[occupants.length - 1];
        int index = 0;

        // Copy all occupants except the adventurer to the new array
        for (Character character : occupants) {
            if (!(character instanceof Adventurer && character.equals(occupant))) {
                newOccupants[index++] = character;
            }
        }

        // Update the occupants array
        this.occupants = newOccupants;

        // Return the removed adventurer
        return occupant;
    }

    /**
     * toString method of the Room to print out its occupants
     * @return String version of the room
     *
     * Ex. "Northwest: No occupants are here."
     * Ex. "Southeast: Adventurer testAdventurer is here."
     */
    @Override
    public String toString() {
        String result = name + ":\n";

        // If the room is empty
        if (occupants.length == 0) {
            result += "No occupants are here.";
        } else {
            // Add information about each occupant
            for (Character occupant : occupants) {
                String occupantType = (occupant instanceof Adventurer) ? "Adventurer" : "Creature";
                result += occupantType + " " + occupant + " is here. ";
            }
        }

        return result;
    }

}

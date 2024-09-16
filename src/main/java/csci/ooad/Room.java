package csci.ooad;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private String name;
    private List<Character> occupants;
    private int index;


    /**
     * Default constructor, initializes name, occupants, and index to default values
     */
    public Room() {
        this.name = "";
        this.occupants = new ArrayList<>(); // Initializes with an empty array
        this.index = -1;//
    }


    /**
     * Room constructor with a room name. Initializes the room name to name and
     * occupants and room index to default values
     * @param name - name of Room
     */
    public Room(String name){
        this.name = name;
        this.occupants = new ArrayList<>(); // Initializes with an empty a
        this.index = -1;
    }

    /**
     * Room constructor with name and index to initialize, also initializes an empty array for occupants
     * @param name - name of Room
     * @param index - index of Room
     */
    public Room(String name,int index) {
        this.name = name;
        this.occupants = new ArrayList<>(); // Initialize with an empty array
        this.index = index;
    }

    // ------------------ METHODS ------------------


    public String getName() {
        return name;
    }

    public int getIndex() {return index;}

    public List<Character> getOccupants() {
        return occupants;
    }



    /**
     * Method to add a Character to a room as an occupant
     * @param occupant - Character object to add to the room
     */
    public void addOccupant(Character occupant) {
        occupants.add(occupant);
    }


    /**
     * Method to remove an Adventurer occupant from a Room
     * @param occupant - Adventurer to remove from Room
     * @return the Adventurer that was removed
     */
    public Adventurer removeAdventurer(Adventurer occupant) {
        // Check if the occupant is in the room
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

        // Remove the adventurer from the list of occupants
        occupants.remove(occupant);

        // Return the removed adventurer
        return occupant;
    }


    public void emptyRoom() {
        occupants.clear();
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
        if (occupants.isEmpty()) {
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

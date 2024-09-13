package csci.ooad;

public class Room {

    private String name;
    private Character [] occupants;

    // Default constructor
    public Room() {
        this.name = "";
        this.occupants = new Character[0]; // Initializes with an empty array
    }

    // Constructor with name
    public Room(String name) {
        this.name = name;
        this.occupants = new Character[0]; // Initialize with an empty array
    }

    public String getName() {
        return name;
    }

    // Getter for occupants
    public Character[] getOccupants() {
        return occupants;
    }

    // Add character to occupants
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

    // Remove adventurer from occupants
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
                result += occupantType + " " + occupant + " is here.\n";
            }
        }

        return result;
    }

}

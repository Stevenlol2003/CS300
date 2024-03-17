/**
 * This class is used to create room objects
 */
public class Room {
    private String location; // the building and room number
    private int capacity; // the maximum number of people who can be in the room at a time

    /**
     * Creates a room object and initializes the data fields to the values of the arguments
     *
     * @param location the building and room number
     * @param capacity the maximum number of people who can be in the room at a time
     * @throws IllegalArgumentException if the provided integer is negative (<0)
     */
    public Room(String location, int capacity) throws IllegalArgumentException {
        if (capacity < 0) {
            throw new IllegalArgumentException("Integer is negative (<0)");
        }
        this.location = location;
        this.capacity = capacity;
    }

    /**
     * Getter method, returns the location of this room
     *
     * @return returns the location of this room
     */
    public String getLocation() {
        return location;
    }

    /**
     * Getter method, returns the capacity of this room
     *
     * @return the capacity of this room
     */
    public int getCapacity() {
        return capacity;
    }


    /**
     * Returns a new Room object with the same location as this one, but with a capacity less than
     * this one’s by the argument’s amount
     *
     * @param minusCapacity reduce room capacity by this number
     * @return a new Room object with the same location but reduced capacity
     * @throws IllegalArgumentException if the argument is greater than the given Room’s capacity
     */
    public Room reduceCapacity(int minusCapacity) throws IllegalArgumentException {
        if (minusCapacity > capacity) {
            throw new IllegalArgumentException("The argument is greater than the given Room’s " +
                    "capacity");
        }
        int newCapacity = capacity - minusCapacity; // capacity of the new room object
        return new Room(location, newCapacity);
    }
}

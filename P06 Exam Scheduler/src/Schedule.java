import java.util.Arrays;

/**
 * This class is used to create schedule objects
 */
public class Schedule {
    private Room[] rooms; // an array of the Room objects available for exams
    private Course[] courses; // n array of the Course objects which require exam rooms
    private int[] assignments; // an array where the integer at index N is the index of the room
    // that course[N] has been assigned to

    /**
     * Creates a schedule object and initializes the data fields to the values of the arguments, and
     * creates an assignments array of the correct length where all values are -1, indicating that
     * the corresponding course has not yet been assigned a room.
     *
     * @param rooms   an array of the Room objects available for exams
     * @param courses n array of the Course objects which require exam rooms
     */
    public Schedule(Room[] rooms, Course[] courses) {
        this.rooms = rooms;
        this.courses = courses;

        // creates an assignments array
        int[] assignments = new int[courses.length];
        Arrays.fill(assignments, -1); // creates an assignments array of the correct length
        // where all values are -1

        this.assignments = assignments;
    }

    /**
     * A private constructor! Initializes the rooms and courses arrays to the provided values and
     * assignments to the provided assignments array. May assume the assignments array is the
     * correct length (equal to the length of the courses array).
     *
     * @param rooms       an array of the Room objects available for exams
     * @param courses     n array of the Course objects which require exam rooms
     * @param assignments an array where the integer at index N is the index of the room that
     *                    course[N] has been assigned to
     */
    private Schedule(Room[] rooms, Course[] courses, int[] assignments) {
        this.rooms = rooms;
        this.courses = courses;
        this.assignments = assignments;
    }

    /**
     * Getter method, returns the number of rooms available in this schedule
     *
     * @return the number of rooms available in this schedule
     */
    public int getNumRooms() {
        return rooms.length;
    }

    /**
     * Getter method, returns the Room object at the given index in the rooms array
     *
     * @return the Room object at the given index in the rooms array
     * @throws IndexOutOfBoundsException if the given index is invalid
     */
    public Room getRoom(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= rooms.length) {
            throw new IndexOutOfBoundsException("Index is invalid");
        }

        return rooms[index];
    }

    /**
     * Returns the number of courses in this schedule
     *
     * @return the number of courses in this schedule
     */
    public int getNumCourses() {
        return courses.length;
    }

    /**
     * Returns the Course object at the given index in the rooms array
     *
     * @return the Couse object at the given index in the rooms array
     * @throws IndexOutOfBoundsException if the given index is invalid
     */
    public Course getCourse(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= courses.length) {
            throw new IndexOutOfBoundsException("Index is invalid");
        }

        return courses[index];
    }

    /**
     * Checks to see if the course at the given index has been assigned a room
     *
     * @param index index of the course to be checked
     * @return true if and only if the course at the given index has been assigned a room; false
     * otherwise
     */
    public boolean isAssigned(int index) {
        return assignments[index] != -1;
    }

    /**
     * Returns the Room object assigned to the course at the given index
     *
     * @param index index of the course
     * @return the Room object assigned to the course at the given index
     * @throws IndexOutOfBoundsException if the given course index is invalid
     * @throws IllegalArgumentException  if the course has not been assigned a room
     */
    public Room getAssignment(int index) throws IndexOutOfBoundsException,
            IllegalArgumentException {
        if (index < 0 || index >= courses.length) {
            throw new IndexOutOfBoundsException("Index is invalid");
        }
        if (!isAssigned(index)) {
            throw new IllegalArgumentException("The course has not been assigned a room");
        }

        return getRoom(assignments[index]);
    }

    /**
     * Checks if all courses have been assigned to rooms
     *
     * @return true if and only if all courses have been assigned to rooms; false otherwise
     */
    public boolean isComplete() {
        boolean isComplete = true;
        for (int i = 0; i < courses.length; ++i) {
            if (!isAssigned(i)) {
                isComplete = false; // if any course is unassigned, boolean becomes false
            }
        }

        return isComplete;
    }

    /**
     * Returns a new Schedule object with the course at the first argument index assigned to the
     * room at the second argument index
     *
     * @param index     index of the course object being assigned
     * @param roomIndex index of the room assigned to
     * @return a new Schedule object with the course at the first argument index assigned to the
     * room at the second argument index
     * @throws IndexOutOfBoundsException if the given course or room index is invalid
     * @throws IllegalArgumentException  if the given course has already been assigned a room, or if
     *                                   the room does not have sufficient capacity.
     */
    public Schedule assignCourse(int index, int roomIndex) throws IndexOutOfBoundsException,
            IllegalArgumentException {
        if (index < 0 || index >= courses.length) {
            throw new IndexOutOfBoundsException("Course index is invalid");
        }
        if (roomIndex < 0 || index >= rooms.length) {
            throw new IndexOutOfBoundsException("Room index is invalid");
        }
        if (isAssigned(index)) {
            throw new IllegalArgumentException("Course has already been assigned to a room");
        }
        if (rooms[roomIndex].getCapacity() < courses[index].getNumStudents()) {
            throw new IllegalArgumentException("Room does not have sufficient capacity");
        }

        Room[] copyRooms = Arrays.copyOf(rooms, rooms.length); // creates a deep copy of room
        // object array

        int[] copyAssignments = Arrays.copyOf(assignments, assignments.length); // creates a deep
        // copy of assignments array

        Schedule newSchedule = new Schedule(copyRooms, courses, copyAssignments); // creates a
        // new schedule

        copyRooms[roomIndex] = rooms[roomIndex].reduceCapacity(courses[index].getNumStudents());
        // reduces room capacity

        newSchedule.assignments[index] = roomIndex; // updates assignments at index of the course
        // to roomIndex for the new schedule object

        return newSchedule;
    }

    /**
     * Prints out the Schedule object with a specific format
     *
     * @return the assignments of courses and rooms in a string format
     */
    @Override
    public String toString() {
        String schedule = "";
        schedule += "{";
        for (int i = 0; i < assignments.length; ++i) {
            if (assignments[i] == -1) {
                schedule += courses[i].getName() + ": Unassigned";
                if (i != (assignments.length - 1)) {
                    schedule += ", ";
                }
            }
            if (assignments[i] != -1) {
                schedule += courses[i].getName() + ": " + rooms[assignments[i]].getLocation();
                if (i != (assignments.length - 1)) {
                    schedule += ", ";
                }
            }
        }
        schedule += "}";

        return schedule;
    }
}

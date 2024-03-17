/**
 * This class is used to create course objects
 */
public class Course {
    private String name; // the name of the course
    private int numStudents; // the number of students enrolled in the course

    /**
     * Creates a course object and initializes the data fields to the values of the arguments
     *
     * @param name        the name of the course
     * @param numStudents the number of students enrolled in the course
     * @throws IllegalArgumentException if the provided integer is negative (<0)
     */
    public Course(String name, int numStudents) throws IllegalArgumentException {
        if (numStudents < 0) {
            throw new IllegalArgumentException("Integer is negative (<0)");
        }
        this.name = name;
        this.numStudents = numStudents;
    }

    /**
     * Getter method, returns the name of this course
     *
     * @return the name of this course
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method, returns the number of students enrolled in this course
     *
     * @return the number of students enrolled in this course
     */
    public int getNumStudents() {
        return numStudents;
    }
}

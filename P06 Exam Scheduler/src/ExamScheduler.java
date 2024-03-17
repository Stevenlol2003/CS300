import java.util.ArrayList;

/**
 * This class contains the recursive methods which creates exam schedules
 */
public class ExamScheduler {
    /**
     * Returns a valid Schedule for the given set of rooms and courses
     *
     * @param rooms   rooms to be assigned to
     * @param courses courses to assign to rooms
     * @return a valid Schedule for the given set of rooms and courses
     * @throws IllegalStateException if no valid schedule exists
     */
    public static Schedule findSchedule(Room[] rooms, Course[] courses)
            throws IllegalStateException {

        return findScheduleHelper(new Schedule(rooms, courses), 0);
    }

    /**
     * Recursive method that assigns all unassigned courses in a Schedule beginning from the index
     * provided as an argument
     *
     * @param schedule a schedule object to be filled
     * @param index    recursive method assigns all unassigned courses in a Schedule beginning from
     *                 this index
     * @return the resulting schedule
     * @throws IllegalStateException if the provided index is equal to the number of courses but
     *                               schedule is not complete
     */
    private static Schedule findScheduleHelper(Schedule schedule, int index)
            throws IllegalStateException {
        // If the provided index is equal to the number of courses, check to see if the Schedule is
        // complete. If so, return the schedule; otherwise throw an IllegalStateException indicating
        // that this Schedule is invalid.
        if (index == schedule.getNumCourses()) {
            if (schedule.isComplete()) {
                return schedule;
            } else {
                throw new IllegalStateException("This schedule is invalid");
            }
        }

        // If the provided index corresponds to a course that has already been assigned to a room,
        // recursively assign the courses at the following indexes and return the resulting
        // schedule.
        if (schedule.isAssigned(index)) {
            findScheduleHelper(schedule, index + 1);
        }

        // If the provided index corresponds to a course that has NOT already been assigned to a
        // room, iteratively try to assign it to each possible valid Room and recursively assign the
        // courses at the following indexes. If this course cannot be assigned to that room, try the
        // next one; return the resulting schedule
        if (!schedule.isAssigned(index)) {
            for (int i = 0; i < schedule.getNumRooms(); ++i) {
                try {
                    schedule.assignCourse(index, i);
                } catch (IllegalArgumentException e) {
                    findScheduleHelper(schedule, index + 1);
                }
            }
        }

        return schedule;
    }

    /**
     * Returns an ArrayList containing all possible Schedules for the given set of rooms and
     * courses. (If none can be created, this ArrayList is empty.) This method should contain only a
     * call to the helper method, providing an empty starting Schedule.
     *
     * @param room   array of rooms
     * @param course array of courses
     * @return an ArrayList containing all possible Schedules for the given set of rooms and courses
     */
    public static ArrayList<Schedule> findAllSchedules(Room[] room, Course[] course) {

        return findAllSchedulesHelper(new Schedule(room, course), 0);
    }

    private static ArrayList<Schedule> findAllSchedulesHelper(Schedule schedule, int index) {
        ArrayList<Schedule> schedules = new ArrayList<>();

        // If the provided index is equal to the number of courses, check to see if the Schedule is
        // complete. If so, add it to an ArrayList of possible schedules and return the ArrayList.
        if (index == schedule.getNumCourses()) {
            if (schedule.isComplete()) {
                schedules.add(schedule);
            }
            return schedules;
        }

        // If the provided index corresponds to a course that has already been assigned to a room,
        // recursively add all possible valid schedules from the following indexes to an
        // ArrayList of Schedules and return this ArrayList.
        if (schedule.isAssigned(index)) {
            return findAllSchedulesHelper(schedule, index + 1);
        }

        // If the provided index corresponds to a course that has NOT already been assigned to a
        // room, iteratively try to assign it to each possible valid Room and recursively add all
        // possible valid schedules from the following indexes to an ArrayList of Schedules and
        // return this ArrayList.
        if (!schedule.isAssigned(index)) {
            for (int i = 0; i < schedule.getNumRooms(); ++i) {
                try {
                    schedule.assignCourse(index, i);
                } catch (IllegalArgumentException e) {

                }
            }
            schedules.add(schedule);
            return findAllSchedulesHelper(schedule, index + 1);
        }

        return schedules;
    }
}

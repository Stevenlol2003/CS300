import java.util.ArrayList;

public class ExamSchedulerTester {
    // main method
    public static void main(String[] args) {
        // System.out.println("testCourse: " + testCourse());
        // System.out.println("testRoom: " + testRoom());
        // System.out.println("testScheduleAccessors() :" + testScheduleAccessors());
        // System.out.println("testAssignCourse :" + testAssignCourse());
        System.out.println("testFindAllSchedules() :" + testFindAllSchedules());
        System.out.println("testFindSchedule(): " + testFindSchedule());
    }

    // tests Course class's constructor and methods
    public static boolean testCourse() {
        boolean testPass = false;

        // tests a course object with correct parameters
        Course CS300 = new Course("CS300", 200);
        if (CS300.getName().equals("CS300") && CS300.getNumStudents() == 200) {
            testPass = true;
        } else {
            return false;
        }

        // tests a course object with bad int parameter, exception should be caught
        try {
            Course badCourse = new Course("CS999", -999);
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            testPass = true;
        }

        return testPass;
    }

    // tests Room class's constructor and methods
    public static boolean testRoom() {
        boolean testPass = false;

        // tests a room object with correct parameters
        Room room1 = new Room("Noland 168", 300);
        if (room1.getLocation().equals("Noland 168") && room1.getCapacity() == 300) {
            testPass = true;
        } else {
            return false;
        }

        // tests a room object with bad int parameter, exception should be caught
        try {
            Room badRoom = new Room("Bad Room", -999);
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            testPass = true;
        }

        // tests the reduceCapacity method
        Room room2 = room1.reduceCapacity(80);
        int expected = 220;
        if (room2.getLocation().equals(room1.getLocation()) && room2.getCapacity() == expected) {
            testPass = true;
        } else {
            return false;
        }

        return testPass;
    }

    // tests Schedule class's accessors
    public static boolean testScheduleAccessors() {
        boolean testPass = false;
        Room room1 = new Room("Noland 168", 300);
        Course CS300 = new Course("CS300", 200);

        Room room2 = new Room("Potato 333", 300);
        Course CS400 = new Course("CS400", 150);

        Room room3 = new Room("Hockey 533", 300);
        Course CS500 = new Course("CS500", 100);

        Room[] rooms = new Room[]{room1, room2, room3};
        Course[] courses = new Course[]{CS300, CS400, CS500};
        Schedule schedule1 = new Schedule(rooms, courses);

        // tests getNumRooms
        if (schedule1.getNumRooms() == 3) {
            testPass = true;
        } else {
            return false;
        }

        // tests getRoom, exception should be caught
        try {
            schedule1.getRoom(-1);
            return false;
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
            testPass = true;
        }

        // tests getNumCourses
        if (schedule1.getNumCourses() == 3) {
            testPass = true;
        } else {
            return false;
        }

        // tests getCourse
        if (schedule1.getCourse(2) == CS500) {
            testPass = true;
        } else {
            return false;
        }

        return testPass;
    }

    // tests assignCourse method
    public static boolean testAssignCourse() {
        boolean testPass = false;
        Room room1 = new Room("SCI 180", 300);
        Room room2 = new Room("HUM 3650", 300);
        Room room3 = new Room("AG 125", 300);

        Course CS300 = new Course("CS300", 200);
        Course CS200 = new Course("CS200", 150);
        Course CS400 = new Course("CS400", 100);

        Room[] rooms = new Room[]{room1, room2, room3};
        Course[] courses = new Course[]{CS300, CS200, CS400};
        int[] assignments = new int[]{-1, -1, -1};

        Schedule schedule = new Schedule(rooms, courses);

        schedule = schedule.assignCourse(0, 2);
        schedule = schedule.assignCourse(1, 1);

        System.out.println("{" + courses[0].getName() + ": " + rooms[2].getLocation() + ", " +
                courses[1].getName() + ": " + rooms[1].getLocation() + ", " +
                courses[2].getName() + ": Unassigned}");

        String expected =
                "{" + courses[0].getName() + ": " + rooms[2].getLocation() + ", " +
                        courses[1].getName() + ": " + rooms[1].getLocation() + ", " +
                        courses[2].getName() + ": Unassigned}";

        String toStringSchedule = schedule.toString();

        System.out.println(toStringSchedule);

        if (expected.equals(toStringSchedule)) {
            testPass = true;
        } else {
            return false;
        }

        return testPass;
    }

    //tests findAllSchedules method
    public static boolean testFindAllSchedules() {
        boolean testPass = false;

        // tests an impossible case, should return empty ArrayList
        Room room1 = new Room("SCI 180", 75);
        Room room2 = new Room("HUM 3650", 100);
        Room room3 = new Room("AG 125", 15);

        Course CS300 = new Course("CS300", 20);
        Course CS200 = new Course("CS200", 110);
        Course CS400 = new Course("CS400", 80);

        Room[] rooms = new Room[]{room1, room2, room3};
        Course[] courses = new Course[]{CS300, CS200, CS400};

        ArrayList<Schedule> assignments = ExamScheduler.findAllSchedules(rooms, courses);

        // System.out.println(assignments);

        ArrayList<Schedule> expected = new ArrayList<>();

        if (assignments.equals(expected)) {
            testPass = true;
        } else {
            return false;
        }

        // tests a correct case
        Room room4 = new Room("SCI 555", 75);
        Room room5 = new Room("HUM 444", 100);
        Room room6 = new Room("AG 333", 15);

        Course CS220 = new Course("CS220", 15);
        Course CS401 = new Course("CS401", 80);
        Course CS557 = new Course("CS557", 70);

        Room[] rooms2 = new Room[]{room4, room5, room6};
        Course[] courses2 = new Course[]{CS220, CS401, CS557};

        ArrayList<Schedule> assignments2 = ExamScheduler.findAllSchedules(rooms2, courses2);

        System.out.println(assignments2);

        ArrayList<Schedule> expected2 = new ArrayList<>();

        if (assignments2.equals(expected2)) {
            testPass = true;
        } else {
            return false;
        }


        return testPass;
    }

    // tests findSchedule method
    public static boolean testFindSchedule() {
        boolean testPass = false;

        Room room1 = new Room("SCI 180", 15);
        Room room2 = new Room("HUM 3650", 15);
        Room room3 = new Room("AG 125", 15);

        Course CS300 = new Course("CS300", 15);
        Course CS200 = new Course("CS200", 15);
        Course CS400 = new Course("CS400", 15);

        Room[] rooms = new Room[]{room1, room2, room3};
        Course[] courses = new Course[]{CS300, CS200, CS400};

        Schedule schedule = ExamScheduler.findSchedule(rooms, courses);
        String toStringSchedule = schedule.toString();

        String expected = "{CS300: Unassigned, CS200: Unassigned, CS400: Unassigned}";
        if (expected.equals(toStringSchedule)) {
            testPass = true;
        } else {
            return false;
        }

        return testPass;
    }

}

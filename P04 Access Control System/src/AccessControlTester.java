/**
 * This class contains tester methods for the User and AccessControl classes
 */
public class AccessControlTester {
    /**
     * Main method
     *
     * @param args input arguments if any
     */
    public static void main(String[] args) {
        System.out.println("testUserConstructorAndMethods(): " + testUserConstructorAndMethods());
        System.out.println("testAccessControlIsValidLoginNotValidUser(): " +
                testAccessControlIsValidLoginNotValidUser());
        System.out.println("testAddUserWithNoAdminPowers(): " + testAddUserWithNoAdminPowers());
        System.out.println("testAddRemoveUserWithAdminPowers(): " +
                testAddRemoveUserWithAdminPowers());
        System.out.println("runAllTests(): " + runAllTests());
    }

    // Runs all tests
    public static boolean runAllTests() {
        return testUserConstructorAndMethods() && testAccessControlIsValidLoginNotValidUser() &&
                testAddUserWithNoAdminPowers() && testAddRemoveUserWithAdminPowers();
    }

    //tests User class's method
    public static boolean testUserConstructorAndMethods() {
        User newUser = new User("Steven", "testpassword", true);

        // tests isValidLogin method
        if (!newUser.isValidLogin("testpassword")) {
            System.out.println("Password doesn't match");
            return false;
        }

        //tests getUsername method
        if (!newUser.getUsername().equals("Steven")) {
            System.out.println("Username doesn't match");
            return false;
        }

        //tests getIsAdmin method
        if (!newUser.getIsAdmin() == true) {
            System.out.println("Admin status doesn't match");
            return false;
        }

        //tests setPassword method
        newUser.setPassword("newpassword");
        if (!newUser.isValidLogin("newpassword")) {
            System.out.println("Password doesn't match");
            return false;
        }

        //tests setIsAdmin method
        newUser.setIsAdmin(false);
        if (!newUser.getIsAdmin() == false) {
            System.out.println("Admin status doesn't match");
            return false;
        }

        return true;
    }

    //tests isValidLogin method
    public static boolean testAccessControlIsValidLoginNotValidUser() {
        // Checks the correctness of AccessControl.isValidLogin() method when called with incorrect
        // username or not matching (username, password) pair
        User newUser = new User("MyUsername", "GoodPassword", false);
        boolean test1 = AccessControl.isValidLogin("BadUsername", "GoodPassword");
        boolean test2 = AccessControl.isValidLogin("BadUsername", "BadPassword");

        if (test1 == false && test2 == false) {
            return true;
        }

        return false;
    }

    // tests addUser method with no admin powers
    public static boolean testAddUserWithNoAdminPowers() {
        // Creates a new AccessControl object and does not log in an admin. This test must fail if
        // addUser(String username) does not return false or if a user was added to the list of
        // user after the method returns.
        AccessControl newObject = new AccessControl();
        User newUser = new User("notadmin", "notadmin", false);
        newObject.setCurrentUser("notadmin");

        if (newObject.addUser("testinguser") == false) {
            return true;
        }

        return false;
    }

    // tests addUser and removeUser methods with admin powers
    public static boolean testAddRemoveUserWithAdminPowers() {
        // Checks the correctness of addUser and removeUser methods when the current user has
        // admin powers
        AccessControl newObject = new AccessControl();
        newObject.setCurrentUser("admin");

        if (newObject.addUser("TheLegend27") &&
                newObject.removeUser("TheLegend27")) {
            return true;
        }

        return false;
    }
}

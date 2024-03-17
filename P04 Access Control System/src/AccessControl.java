import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This instance represents a single terminal (computer)
 */
public class AccessControl {
    private static ArrayList<User> users; // An ArrayList of valid users
    private User currentUser; // Who is currently logged in, if anyone?
    private static final String DEFAULT_PASSWORD = "changeme"; // Default password given to new
    // users or when we reset a password of a specific user.

    /**
     * A no-argument constructor, creates a new AccessControl object, also checks whether each
     * class variable (static data field) has been initialized and, if not, initialize them
     */
    public AccessControl() {
        if (users == null) {
            users = new ArrayList<>();
            User newUser = new User("admin", "root", true);
            users.add(newUser);
            currentUser = null;
        }
    }

    /**
     * Report whether a given username/password pair is a valid login
     *
     * @param username user's username
     * @param password user's password
     * @return true if the username/password pair matches any user in the users ArrayList and
     * false otherwise
     */
    public static boolean isValidLogin(String username, String password) {
        if (users != null) {
            for (int i = 0; i < users.size(); ++i) {
                if (users.get(i).getUsername().equals(username) &&
                        users.get(i).isValidLogin(password)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Log out the current user
     *
     */
    public void logout() {
        currentUser = null;
    }

    /**
     * Change the current password of the current user
     *
     * @param newPassword user's new password
     */
    public void changePassword(String newPassword) {
        currentUser.setPassword(newPassword);
    }

    /**
     * A mutator that you can use to write tests without simulating user input. It sets the
     * current user to the user from the users list whose username matches the string provided as
     * input to the method (exact match case sensitive).
     *
     * @param username user's username
     */
    public void setCurrentUser(String username) {
        for (int i = 0; i < users.size(); ++i) {
            if (users.get(i).getUsername().equals(username)) {
                currentUser = users.get(i);
            }
        }
    }

    /**
     * Create a new user (non-admin) with the default password and isAdmin==false and add it to the
     * users ArrayList
     *
     * @param username user's username
     * @return true if the current user has Admin power and the new user was successfully added,
     * false if the the current user is null or does not have Admin power
     * @throws NoSuchElementException with a descriptive error message if username is null or if its
     *                                length is less than 5 ( < 5), or if a user with the same
     *                                username is already in the list of users (usernames must be
     *                                unique)
     */
    public boolean addUser(String username) throws IllegalArgumentException {
        if (username == null) {
            throw new IllegalArgumentException("username is null");
        }

        if (username.length() < 5) {
            throw new IllegalArgumentException("username length is less than 5");
        }

        for (int i = 0; i < users.size(); ++i) {
            if (users.get(i).getUsername().equals(username)) {
                throw new IllegalArgumentException("username not unique");
            }
        }

        if (currentUser == null || currentUser.getIsAdmin() == false) {
            return false;
        }

        if (currentUser.getIsAdmin()) {
            User newUser = new User(username, DEFAULT_PASSWORD, false);
            users.add(newUser);
        }
        return true;
    }

    /**
     * Create a new user, specify their admin status, and add it to the list of users.
     *
     * @param username user's username
     * @param isAdmin  user's admin status
     * @return true if the current user has Admin power and the new user was successfully added,
     * false if the the current user is null or does not have Admin power
     * @throws NoSuchElementException with a descriptive error message if username is null or if its
     *                                length is less than 5 ( < 5), or if a user with the same
     *                                username is already in the list of users (usernames must be
     *                                unique)
     */
    public boolean addUser(String username, boolean isAdmin) throws IllegalArgumentException {
        if (username == null) {
            throw new IllegalArgumentException("username is null");
        }

        if (username.length() < 5) {
            throw new IllegalArgumentException("username length is less than 5");
        }

        for (int i = 0; i < users.size(); ++i) {
            if (users.get(i).getUsername().equals(username)) {
                throw new IllegalArgumentException("username not unique");
            }
        }

        if (currentUser == null || currentUser.getIsAdmin() == false) {
            return false;
        }

        if (currentUser.getIsAdmin()) {
            User newUser = new User(username, DEFAULT_PASSWORD, isAdmin);
            users.add(newUser);
        }
        return true;
    }

    /**
     * Remove a user given their unique username
     *
     * @param username user's username
     * @return true if the current user has Admin powers and the user whose username is passed as
     * input was successfully removed, false if the the current user is null or does not have Admin
     * power
     * @throws NoSuchElementException with a descriptive error message if no match with username is
     *                                found in the list of users
     */
    public boolean removeUser(String username) throws NoSuchElementException {
        boolean isFound = false;

        for (int i = 0; i < users.size(); ++i) {
            if (users.get(i).getUsername().equals(username)) {
                isFound = true;
                if (currentUser == null || currentUser.getIsAdmin() == false) {
                    return false;
                }
                users.remove(i);
            }
        }

        if (!isFound) {
            throw new NoSuchElementException("No match with username is found in the list of " +
                    "users");
        }

        return true;
    }

    /**
     * Give a user admin power given their username
     *
     * @param username user's username
     * @return true if this operation terminates successfully, false if the current user is null or
     * does not have admin powers
     * @throws NoSuchElementException with a descriptive error message if no match with username is
     *                                found in the list of users
     */
    public boolean giveAdmin(String username) throws NoSuchElementException {
        boolean isFound = false;

        for (int i = 0; i < users.size(); ++i) {
            if (users.get(i).getUsername().equals(username)) {
                isFound = true;
                if (currentUser == null || currentUser.getIsAdmin() == false) {
                    return false;
                }
                users.get(i).setIsAdmin(true);
            }
        }

        if (!isFound) {
            throw new NoSuchElementException("No match with username is found in the list of " +
                    "users");
        }

        return true;

    }

    /**
     * Remove the admin power of a user given their username
     *
     * @param username user's username
     * @return true if this operation terminates successfully, false if the current user is null or
     * does not have admin powers
     * @throws NoSuchElementException with a descriptive error message if no match with username is
     *                                found in the list of users
     */
    public boolean takeAdmin(String username) throws NoSuchElementException {
        boolean isFound = false;

        for (int i = 0; i < users.size(); ++i) {
            if (users.get(i).getUsername().equals(username)) {
                isFound = true;
                if (currentUser == null || currentUser.getIsAdmin() == false) {
                    return false;
                }
                users.get(i).setIsAdmin(false);
            }
        }

        if (!isFound) {
            throw new NoSuchElementException("No match with username is found in the list of " +
                    "users");
        }

        return true;
    }

    /**
     * Reset the password of a user given their username
     *
     * @param username user's username
     * @return true if this operation terminates successfully, false if the current user is null or
     * does not have admin powers
     * @throws NoSuchElementException with a descriptive error message if no match with username is
     *                                found in the list of users
     */
    public boolean resetPassword(String username) throws NoSuchElementException {
        boolean isFound = false;

        for (int i = 0; i < users.size(); ++i) {
            if (users.get(i).getUsername().equals(username)) {
                isFound = true;
                if (currentUser == null || currentUser.getIsAdmin() == false) {
                    return false;
                }
                users.get(i).setPassword(DEFAULT_PASSWORD);
            }
        }

        if (!isFound) {
            throw new NoSuchElementException("No match with username is found in the list of " +
                    "users");
        }

        return true;
    }
}

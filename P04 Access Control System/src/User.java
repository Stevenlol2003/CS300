/**
 * This class contains method to create new users, get user info, or change user info
 */
public class User {
    private final String USERNAME; // The name of the user
    private String password; // The password of the user
    private boolean isAdmin; // Whether or not the user has Admin powers

    /**
     * Class constructor, creates a new user with the given username, password, and admin status
     *
     * @param username user's username
     * @param password user's password
     * @param isAdmin  user's admin status
     */
    public User(String username, String password, boolean isAdmin) {
        USERNAME = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    /**
     * Report whether the password is correct
     *
     * @param password user's password
     * @return true if the password is correct, false if not
     */
    public boolean isValidLogin(String password) {
        return this.password.equals(password);
    }

    /**
     * Gets the name of the user
     *
     * @return the name of the user
     */
    public String getUsername() {
        return USERNAME;
    }

    /**
     * Report whether the user is an admin
     *
     * @return true if user is an admin, false if not
     */
    public boolean getIsAdmin() {
        return isAdmin;
    }

    /**
     * Set the new password
     *
     * @param password user's password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Set the new admin status
     *
     * @param isAdmin user's admin status
     */
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}

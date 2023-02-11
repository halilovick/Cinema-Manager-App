package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.FilmoviException;

import java.util.List;

/**
 * Manager class for users.
 */
public class usersManager {
    private boolean userExists(int id) throws FilmoviException {
        usersManager um = new usersManager();
        try {
            User u = um.getById(id);
        } catch (FilmoviException f) {
            return false;
        }
        return true;
    }

    /**
     * Adds user to database.
     *
     * @param u the user
     * @throws FilmoviException the filmovi exception
     */
    public User add(User u) throws FilmoviException {
        if (userExists(u.getUser())) throw new FilmoviException("User with such username already exists!");
        try {
            return DaoFactory.usersDao().add(u);
        } catch (FilmoviException e) {
            throw new FilmoviException(e.getMessage(), e);
        }
    }

    /**
     * Deletes user from database.
     *
     * @param userId the user id
     * @throws FilmoviException the filmovi exception
     */
    public void delete(int userId) throws FilmoviException {
        if (!userExists(userId)) throw new FilmoviException("User does not exist!");
        try {
            DaoFactory.usersDao().delete(userId);
        } catch (FilmoviException e) {
            throw new FilmoviException(e.getMessage(), e);
        }
    }

    /**
     * Updates user in database.
     *
     * @param u the user
     * @throws FilmoviException the filmovi exception
     */
    public User update(User u) throws FilmoviException {
        return DaoFactory.usersDao().update(u);
    }

    /**
     * Gets all users from database.
     *
     * @return list with all the users.
     * @throws FilmoviException the filmovi exception
     */
    public List<User> getAll() throws FilmoviException {
        return DaoFactory.usersDao().getAll();
    }

    /**
     * Gets id from logged in user.
     *
     * @param username the username
     * @param password the password
     * @return the id
     * @throws FilmoviException the filmovi exception
     */
    public int getLoggedInId(String username, String password) throws FilmoviException {
        return DaoFactory.usersDao().getLoggedInId(username, password);
    }

    /**
     * Gets user by id.
     *
     * @param id the user id
     * @return the user by id
     * @throws FilmoviException the filmovi exception
     */
    public User getById(int id) throws FilmoviException {
        return DaoFactory.usersDao().getById(id);
    }

    /**
     * Checks if user exists
     *
     * @param username the username
     * @return the boolean
     * @throws FilmoviException the filmovi exception
     */
    public boolean userExists(String username) throws FilmoviException {
        return DaoFactory.usersDao().userExists(username);
    }

    /**
     * Gets user by username.
     *
     * @param username the username
     * @return the user by username
     * @throws FilmoviException the filmovi exception
     */
    public User getByUsername(String username) throws FilmoviException {
        return DaoFactory.usersDao().getByUsername(username);
    }
}

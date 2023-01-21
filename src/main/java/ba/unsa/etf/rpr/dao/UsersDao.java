package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.FilmoviException;

/**
 * The interface Users dao.
 */
public interface UsersDao extends Dao<User>{
    /**
     * Gets logged in id.
     *
     * @param username the username
     * @param password the password
     * @return the logged in id
     * @throws FilmoviException the filmovi exception
     */
    int getLoggedInId(String username, String password) throws FilmoviException;

    /**
     * User exists boolean.
     *
     * @param username the username
     * @return the boolean if user exists
     * @throws FilmoviException the filmovi exception
     */
    boolean userExists(String username) throws FilmoviException;
}

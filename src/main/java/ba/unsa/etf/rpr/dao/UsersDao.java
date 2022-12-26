package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.FilmoviException;

public interface UsersDao extends Dao<User>{
    int getLoggedInId(String username, String password) throws FilmoviException;
    public boolean userExists(String username) throws FilmoviException;
}

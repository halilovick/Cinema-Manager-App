package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;

public interface UsersDao extends Dao<User>{
    int getLoggedInId(String username, String password);
    int getLoggedInAdmin(String username, String password);
}

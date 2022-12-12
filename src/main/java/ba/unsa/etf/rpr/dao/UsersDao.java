package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;

import java.sql.SQLException;

public interface UsersDao extends Dao<User>{
    Boolean loginCheck(String username, String password) throws SQLException;
    int getLoggedInId(String username, String password);
}

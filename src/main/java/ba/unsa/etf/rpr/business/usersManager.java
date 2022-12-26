package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.FilmoviException;

import java.util.List;

public class usersManager {
    public User add(User u) throws FilmoviException {
        try {
            return DaoFactory.usersDao().add(u);
        } catch (FilmoviException e) {
            throw new FilmoviException(e.getMessage(), e);
        }
    }

    public void delete(int userId) throws FilmoviException {
        try {
            DaoFactory.usersDao().delete(userId);
        } catch (FilmoviException e) {
            throw new FilmoviException(e.getMessage(), e);
        }
    }

    public User update(User u) throws FilmoviException {
        return DaoFactory.usersDao().update(u);
    }

    public List<User> getAll() throws FilmoviException {
        return DaoFactory.usersDao().getAll();
    }

    public int getLoggedInId(String username, String password) throws FilmoviException {
        return DaoFactory.usersDao().getLoggedInId(username, password);
    }

    public User getById(int id) throws FilmoviException {
        return DaoFactory.usersDao().getById(id);
    }

    public boolean userExists(String username) throws FilmoviException {
        return DaoFactory.usersDao().userExists(username);
    }
}

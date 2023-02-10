package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.FilmoviException;

import java.util.List;

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

    public User add(User u) throws FilmoviException {
        if (userExists(u.getUser())) throw new FilmoviException("User with such username already exists!");
        try {
            return DaoFactory.usersDao().add(u);
        } catch (FilmoviException e) {
            throw new FilmoviException(e.getMessage(), e);
        }
    }

    public void delete(int userId) throws FilmoviException {
        if (!userExists(userId)) throw new FilmoviException("User does not exist!");
        try {
            DaoFactory.usersDao().delete(userId);
        } catch (FilmoviException e) {
            throw new FilmoviException(e.getMessage(), e);
        }
    }

    public User update(User u) throws FilmoviException {
        if (userExists(u.getUser())) throw new FilmoviException("User with such username already exists!");
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

    public User getByUsername(String username) throws FilmoviException {
        return DaoFactory.usersDao().getByUsername(username);
    }
}

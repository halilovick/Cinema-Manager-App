package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.FilmoviException;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class UsersDaoSQLImpl extends AbstractDao<User> implements UsersDao {
    private static UsersDaoSQLImpl instance = null;

    public UsersDaoSQLImpl() {
        super("users");
    }

    public static UsersDaoSQLImpl getInstance() {
        if (instance == null) instance = new UsersDaoSQLImpl();
        return instance;
    }

    public static void removeInstance() {
        if (instance != null) instance = null;
    }

    @Override
    public User row2object(ResultSet rs) throws FilmoviException {
        try {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUser(rs.getString("user"));
            user.setPassword(rs.getString("password"));
            user.setIme(rs.getString("ime"));
            user.setEmail(rs.getString("email"));
            user.setGrad(rs.getString("grad"));
            user.setAdresa(rs.getString("adresa"));
            user.setDatum_rodjenja(rs.getDate("datum_rodjenja"));
            user.setAdmin(rs.getBoolean("admin"));
            return user;
        } catch (Exception e) {
            throw new FilmoviException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(User object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.getId());
        item.put("user", object.getUser());
        item.put("password", object.getPassword());
        item.put("ime", object.getIme());
        item.put("email", object.getEmail());
        item.put("adresa", object.getAdresa());
        item.put("grad", object.getGrad());
        item.put("datum_rodjenja", object.getDatum_rodjenja());
        item.put("admin", object.getAdmin());
        return item;
    }

    @Override
    public void resetIncrement(int i) throws FilmoviException {
        try {
            executeQuery("ALTER TABLE users AUTO_INCREMENT = ?", new Object[]{i});
        } catch (FilmoviException e) {
            throw new FilmoviException(e.getMessage(), e);
        }
    }

    @Override
    public int getLoggedInId(String username, String password) throws FilmoviException {
        try {
            List<User> l = executeQuery("SELECT * FROM users WHERE user = ? AND password = ?", new Object[]{username, password});
            if (l.isEmpty()) return 0;
            return l.get(0).getId();
        } catch (FilmoviException e) {
            throw new FilmoviException(e.getMessage(), e);
        }
    }

    @Override
    public boolean userExists(String username) throws FilmoviException {
        try {
            User u = executeQueryUnique("SELECT * FROM users WHERE user = ?", new Object[]{username});
            return true;
        } catch (FilmoviException f) {
            return false;
        }
    }
}
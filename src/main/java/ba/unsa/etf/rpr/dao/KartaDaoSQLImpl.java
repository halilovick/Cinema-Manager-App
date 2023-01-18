package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Karta;
import ba.unsa.etf.rpr.exceptions.FilmoviException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.TreeMap;

public class KartaDaoSQLImpl extends AbstractDao<Karta> implements KartaDao {
    private static KartaDaoSQLImpl instance = null;


    public KartaDaoSQLImpl() {
        super("karta");
    }

    public static KartaDaoSQLImpl getInstance() {
        if (instance == null) instance = new KartaDaoSQLImpl();
        return instance;
    }

    public static void removeInstance() {
        if (instance != null) instance = null;
    }

    @Override
    public Karta row2object(ResultSet rs) throws FilmoviException {
        try {
            Karta k = new Karta();
            k.setId(rs.getInt("id"));
            k.setUser(DaoFactory.usersDao().getById(rs.getInt("user_id")));
            k.setFilm(DaoFactory.filmDao().getById(rs.getInt("film_id")));
            return k;
        } catch (Exception e) {
            throw new FilmoviException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Karta object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.getId());
        item.put("user_id", object.getUser().getId());
        item.put("film_id", object.getFilm().getId());
        return item;
    }

    @Override
    public void resetIncrement(int i) throws FilmoviException {
        String query = "ALTER TABLE karta AUTO_INCREMENT = ?";
        try {
            PreparedStatement stmt = this.getConnection().prepareStatement(query);
            stmt.setObject(1, i);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // poor error handling
        }
    }

    @Override
    public void deleteAll() throws FilmoviException {
        String query = "DELETE FROM karta";
        try {
            PreparedStatement stmt = this.getConnection().prepareStatement(query);
            stmt.executeUpdate();
            KartaDao k = new KartaDaoSQLImpl();
            k.resetIncrement(1);
        } catch (SQLException e) {
            e.printStackTrace(); // poor error handling
        }
    }

    @Override
    public void deleteWithFilmId(int film_id) throws FilmoviException {
        String sql = "DELETE FROM karta WHERE film_id = ?";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, film_id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new FilmoviException(e.getMessage(), e);
        }
    }
}

package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Karta;
import ba.unsa.etf.rpr.exceptions.FilmoviException;

import java.sql.ResultSet;
import java.util.Map;
import java.util.TreeMap;

public class KartaDaoSQLImpl extends AbstractDao<Karta> implements KartaDao {

    public KartaDaoSQLImpl() {
        super("karta");
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
        try {
            executeQuery("ALTER TABLE karta AUTO_INCREMENT = ?", new Object[]{i});
        } catch (FilmoviException e) {
            throw new FilmoviException(e.getMessage(), e);
        }
    }

    @Override
    public void deleteAll() throws FilmoviException {
        try {
            executeQuery("DELETE FROM karta", new Object[]{});
        } catch (FilmoviException e) {
            throw new FilmoviException(e.getMessage(), e);
        }
    }
}

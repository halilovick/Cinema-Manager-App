package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Film;
import ba.unsa.etf.rpr.exceptions.FilmoviException;

import java.sql.*;
import java.util.*;

public class FilmDaoSQLImpl extends AbstractDao<Film> implements FilmDao {
    public FilmDaoSQLImpl() {
        super("film");
    }

    @Override
    public Film row2object(ResultSet rs) throws FilmoviException {
        try {
            Film f = new Film();
            f.setId(rs.getInt("id"));
            f.setIme(rs.getString("ime"));
            f.setZanr(rs.getString("zanr"));
            f.setTrajanje(rs.getInt("trajanje"));
            f.setCijena(rs.getInt("cijena"));
            f.setBroj_sale(rs.getInt("broj_sale"));
            return f;
        } catch (SQLException e) {
            throw new FilmoviException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Film object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.getId());
        item.put("ime", object.getIme());
        item.put("zanr", object.getZanr());
        item.put("trajanje", object.getTrajanje());
        item.put("cijena", object.getCijena());
        item.put("broj_sale", object.getBroj_sale());
        return item;
    }

    @Override
    public void resetIncrement(int i) throws FilmoviException {
        try {
            executeQuery("ALTER TABLE film AUTO_INCREMENT = ?", new Object[]{i});
        } catch (FilmoviException e) {
            throw new FilmoviException(e.getMessage(), e);
        }
    }

    @Override
    public List<Film> searchByTrajanje(int a, int b) throws FilmoviException {
        try {
            return executeQuery("SELECT * FROM film WHERE trajanje BETWEEN ? AND ?", new Object[]{a, b});
        } catch (FilmoviException e) {
            throw new FilmoviException(e.getMessage(), e);
        }
    }

    @Override
    public List<Film> searchByZanr(String zanr) throws FilmoviException {
        try {
            return executeQuery("SELECT * FROM film WHERE zanr = ?", new Object[]{zanr});
        } catch (FilmoviException e) {
            throw new FilmoviException(e.getMessage(), e);
        }
    }

    @Override
    public List<String> getAllNames() throws FilmoviException {
        String query = "SELECT * FROM film";
        List<String> filmovi = new ArrayList<String>();
        try {
            PreparedStatement stmt = this.getConnection().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) { // result set is iterator.
                filmovi.add(rs.getString("ime"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace(); // poor error handling
        }
        return filmovi;
    }

    @Override
    public Film getByIme(String ime) throws FilmoviException {
        try {
            List<Film> filmovi = executeQuery("SELECT * FROM film WHERE ime = ?", new Object[]{ime});
            return filmovi.get(0);
        } catch (FilmoviException e) {
            throw new FilmoviException(e.getMessage(), e);
        }
    }
}

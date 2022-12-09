package ba.unsa.etf.rpr;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KartaDaoSQLImpl implements KartaDao{
    private Connection connection;

    public KartaDaoSQLImpl() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7583502", "sql7583502", "ez7bJNj2sl");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public Karta getById(int id) {
        String query = "SELECT * FROM karta WHERE id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Karta karta = new Karta();
                karta.setId(rs.getInt("id"));
                karta.setKupac_id(rs.getInt("kupac_id"));
                karta.setFilm_id(rs.getInt("film_id"));
                karta.setCijena(rs.getInt("cijena"));
                karta.setBroj_sale(rs.getInt("broj_sale"));
                rs.close();
                return karta;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Karta add(Karta item) {
        String insert = "INSERT INTO karta(kupac_id, film_id, cijena, broj_sale) VALUES(?,?,?,?)";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, item.getKupac_id());
            stmt.setInt(2, item.getFilm_id());
            stmt.setInt(3, item.getCijena());
            stmt.setInt(4, item.getBroj_sale());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next(); // we know that there is one key
            item.setId(rs.getInt(1)); //set id to return it back
            return item;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Karta update(Karta item) {
        String insert = "UPDATE karta SET kupac_id = ?, film_id = ?, cijena = ?, broj_sale = ? WHERE id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, item.getKupac_id());
            stmt.setObject(2, item.getFilm_id());
            stmt.setObject(3, item.getCijena());
            stmt.setObject(4, item.getBroj_sale());
            stmt.setObject(5, item.getId());
            stmt.executeUpdate();
            return item;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(int id) {
        String insert = "DELETE FROM karta WHERE id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Karta> getAll() {
        String query = "SELECT * FROM karta";
        List<Karta> karte = new ArrayList<Karta>();
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) { // result set is iterator.
                Karta karta = new Karta();
                karta.setId(rs.getInt("id"));
                karta.setKupac_id(rs.getInt("kupac_id"));
                karta.setFilm_id(rs.getInt("film_id"));
                karta.setCijena(rs.getInt("cijena"));
                karta.setBroj_sale(rs.getInt("broj_sale"));
                karte.add(karta);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace(); // poor error handling
        }
        return karte;
    }
}

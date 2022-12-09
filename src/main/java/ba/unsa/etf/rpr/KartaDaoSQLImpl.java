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
                karta.setIme_filma(rs.getString("ime_filma"));
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
        String insert = "INSERT INTO karta(ime_filma) VALUES(?)";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, item.getIme_filma());
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
        String insert = "UPDATE karta SET ime_filma = ? WHERE id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, item.getIme_filma());
            stmt.setObject(2, item.getId());
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
                karta.setIme_filma(rs.getString("ime_filma"));
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

package ba.unsa.etf.rpr;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KupacDaoSQLImpl implements KupacDao{
    private Connection connection;

    public KupacDaoSQLImpl() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7583502", "sql7583502", "ez7bJNj2sl");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public Kupac getById(int id) {
        String query = "SELECT * FROM kupac WHERE id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Kupac kupac = new Kupac();
                kupac.setId(rs.getInt("id"));
                kupac.setKupac_ime(rs.getString("kupac_ime"));
                kupac.setVrijeme_kupovine(rs.getTimestamp("vrijeme_kupovine"));
                rs.close();
                return kupac;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Kupac add(Kupac item) {
        String insert = "INSERT INTO kupac(kupac_ime, vrijeme_kupovine) VALUES(?, ?)";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, item.getKupac_ime());
            stmt.setTimestamp(2, item.getVrijeme_kupovine());
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
    public Kupac update(Kupac item) {
        String insert = "UPDATE kupac SET kupac_ime = ?, vrijeme_kupovine = ? WHERE id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, item.getKupac_ime());
            stmt.setObject(2, item.getVrijeme_kupovine());
            stmt.setObject(3, item.getId());
            stmt.executeUpdate();
            return item;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(int id) {
        String insert = "DELETE FROM kupac WHERE id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Kupac> getAll() {
        String query = "SELECT * FROM kupac";
        List<Kupac> kupci = new ArrayList<Kupac>();
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) { // result set is iterator.
                Kupac kupac = new Kupac();
                kupac.setId(rs.getInt("id"));
                kupac.setKupac_ime(rs.getString("kupac_ime"));
                kupac.setVrijeme_kupovine(rs.getTimestamp("vrijeme_kupovine"));
                kupci.add(kupac);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace(); // poor error handling
        }
        return kupci;
    }
}

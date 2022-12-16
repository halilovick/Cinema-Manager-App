package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Karta;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class KartaDaoSQLImpl implements KartaDao {
    private Connection connection;

    public KartaDaoSQLImpl() {
        String server = new String();
        String user = new String();
        String pass = new String();
        try (InputStream input = new FileInputStream("config.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            server = prop.getProperty("db.url");
            user = prop.getProperty("db.user");
            pass = prop.getProperty("db.password");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            this.connection = DriverManager.getConnection(server, user, pass);
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
                UsersDaoSQLImpl k = new UsersDaoSQLImpl();
                FilmDaoSQLImpl f = new FilmDaoSQLImpl();
                Karta karta = new Karta();
                karta.setId(rs.getInt("id"));
                karta.setUser(k.getById(rs.getInt("user_id")));
                karta.setFilm(f.getById(rs.getInt("film_id")));
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
        String insert = "INSERT INTO karta(user_id, film_id) VALUES(?,?)";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, item.getUser().getId());
            stmt.setInt(2, item.getFilm().getId());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            item.setId(rs.getInt(1));
            return item;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Karta update(Karta item) {
        String insert = "UPDATE karta SET user_id = ?, film_id = ? WHERE id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, item.getUser().getId());
            stmt.setObject(2, item.getFilm().getId());
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
                UsersDaoSQLImpl k = new UsersDaoSQLImpl();
                FilmDaoSQLImpl f = new FilmDaoSQLImpl();
                Karta karta = new Karta();
                karta.setId(rs.getInt("id"));
                karta.setUser(k.getById(rs.getInt("user_id")));
                karta.setFilm(f.getById(rs.getInt("film_id")));
                karte.add(karta);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace(); // poor error handling
        }
        return karte;
    }

    @Override
    public void resetIncrement() {
        String query = "ALTER TABLE karta AUTO_INCREMENT = 1";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // poor error handling
        }
    }

    @Override
    public void deleteAll() {
        String query = "DELETE FROM karta";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.executeUpdate();
            KartaDao k = new KartaDaoSQLImpl();
            k.resetIncrement();
        } catch (SQLException e) {
            e.printStackTrace(); // poor error handling
        }
    }
}

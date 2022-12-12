package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Karta;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KartaDaoSQLImpl implements KartaDao {
    private Connection connection;

    public KartaDaoSQLImpl() {
        String server = new String();
        String user = new String();
        String pass = new String();
        try {
            File f = new File("/Users/khali/Desktop/sqluserpass.txt");
            Scanner sc = new Scanner(f);
            server = sc.nextLine();
            user = sc.nextLine();
            pass = sc.nextLine();
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Greska pri otvaranju file-a!");
            e.printStackTrace();
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
        String insert = "INSERT INTO karta(user_id, film_id, cijena, broj_sale) VALUES(?,?,?,?)";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, item.getUser().getId());
            stmt.setInt(2, item.getFilm().getId());
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
        String insert = "UPDATE karta SET user_id = ?, film_id = ?, cijena = ?, broj_sale = ? WHERE id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, item.getUser().getId());
            stmt.setObject(2, item.getFilm().getId());
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
                UsersDaoSQLImpl k = new UsersDaoSQLImpl();
                FilmDaoSQLImpl f = new FilmDaoSQLImpl();
                Karta karta = new Karta();
                karta.setId(rs.getInt("id"));
                karta.setUser(k.getById(rs.getInt("user_id")));
                karta.setFilm(f.getById(rs.getInt("film_id")));
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
package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Film;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FilmDaoSQLImpl implements FilmDao {
    private Connection connection;

    public FilmDaoSQLImpl() {
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
    public Film getById(int id) {
        String query = "SELECT * FROM film WHERE id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Film film = new Film();
                film.setId(rs.getInt("id"));
                film.setIme(rs.getString("ime"));
                film.setZanr(rs.getString("zanr"));
                film.setTrajanje(rs.getInt("trajanje"));
                rs.close();
                return film;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Film add(Film item) {
        String insert = "INSERT INTO film(ime, zanr, trajanje) VALUES(?, ?, ?)";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, item.getIme());
            stmt.setObject(2, item.getZanr());
            stmt.setObject(3, item.getTrajanje());
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
    public Film update(Film item) {
        String insert = "UPDATE film SET ime = ?, zanr = ?, trajanje = ? WHERE id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, item.getIme());
            stmt.setObject(2, item.getZanr());
            stmt.setObject(3, item.getTrajanje());
            stmt.setObject(4, item.getId());
            stmt.executeUpdate();
            return item;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(int id) {
        String insert = "DELETE FROM film WHERE id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Film> getAll() {
        String query = "SELECT * FROM film";
        List<Film> filmovi = new ArrayList<Film>();
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) { // result set is iterator.
                Film film = new Film();
                film.setId(rs.getInt("id"));
                film.setIme(rs.getString("ime"));
                film.setZanr(rs.getString("zanr"));
                film.setTrajanje(rs.getInt("trajanje"));
                filmovi.add(film);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace(); // poor error handling
        }
        return filmovi;
    }

    @Override
    public List<Film> searchByTrajanje(int a, int b) {
        if(a > b) throw new IllegalArgumentException("Nevalidan unos!");
        String query = "SELECT * FROM film WHERE trajanje BETWEEN ? AND ?";
        List<Film> filmovi = new ArrayList<Film>();
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setObject(1, a);
            stmt.setObject(2, b);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) { // result set is iterator.
                Film film = new Film();
                film.setId(rs.getInt("id"));
                film.setIme(rs.getString("ime"));
                film.setZanr(rs.getString("zanr"));
                film.setTrajanje(rs.getInt("trajanje"));
                filmovi.add(film);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace(); // poor error handling
        }
        return filmovi;
    }

    @Override
    public List<Film> searchByZanr(String z) {
        String query = "SELECT * FROM film WHERE zanr = ?";
        List<Film> filmovi = new ArrayList<Film>();
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setObject(1, z);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) { // result set is iterator.
                Film film = new Film();
                film.setId(rs.getInt("id"));
                film.setIme(rs.getString("ime"));
                film.setZanr(rs.getString("zanr"));
                film.setTrajanje(rs.getInt("trajanje"));
                filmovi.add(film);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace(); // poor error handling
        }
        return filmovi;
    }
}

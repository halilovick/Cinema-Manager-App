package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UsersDaoSQLImpl implements UsersDao {
    private Connection connection;

    public UsersDaoSQLImpl() {
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
    public User getById(int id) {
        String query = "SELECT * FROM users WHERE id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUser(rs.getString("user"));
                user.setPassword(rs.getString("password"));
                user.setIme(rs.getString("ime"));
                user.setEmail(rs.getString("email"));
                user.setGrad(rs.getString("grad"));
                user.setDatum_rodjenja(rs.getDate("datum_rodjenja"));
                user.setAdmin(rs.getBoolean("admin"));
                rs.close();
                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User add(User item) {
        String insert = "INSERT INTO users(user, password, ime, email, adresa, grad, datum_rodjenja, admin) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, item.getUser());
            stmt.setString(2, item.getPassword());
            stmt.setString(3, item.getIme());
            stmt.setString(4, item.getEmail());
            stmt.setString(5, item.getAdresa());
            stmt.setString(6, item.getGrad());
            stmt.setDate(7, item.getDatum_rodjenja());
            stmt.setBoolean(8, item.getAdmin());
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
    public User update(User item) {
        String insert = "UPDATE users SET user = ?, password = ?, ime=?, email=?, adresa=?, grad=?, datum_rodjenja=? WHERE id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, item.getUser());
            stmt.setObject(2, item.getPassword());
            stmt.setObject(3, item.getIme());
            stmt.setObject(4, item.getEmail());
            stmt.setObject(5, item.getAdresa());
            stmt.setObject(6, item.getGrad());
            stmt.setObject(7, item.getDatum_rodjenja());
            stmt.setObject(8, item.getId());
            stmt.executeUpdate();
            return item;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(int id) {
        String insert = "DELETE FROM users WHERE id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAll() {
        String query = "SELECT * FROM users";
        List<User> users = new ArrayList<User>();
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) { // result set is iterator.
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUser(rs.getString("user"));
                user.setPassword(rs.getString("password"));
                user.setIme(rs.getString("ime"));
                user.setEmail(rs.getString("email"));
                user.setGrad(rs.getString("grad"));
                user.setDatum_rodjenja(rs.getDate("datum_rodjenja"));
                user.setAdmin(rs.getBoolean("admin"));
                users.add(user);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace(); // poor error handling
        }
        return users;
    }

    @Override
    public Boolean loginCheck(String username, String password) throws SQLException {
        String query = "SELECT user, password FROM users WHERE user = ? AND password = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if(!rs.next()) return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public int getLoggedInId(String username, String password) {
        String query = "SELECT id, user, password FROM users WHERE user = ? AND password = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) return rs.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

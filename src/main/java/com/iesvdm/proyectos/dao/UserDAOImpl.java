package com.iesvdm.proyectos.dao;

import com.iesvdm.proyectos.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl extends AbstractDAOImpl implements UserDAO {

    @Override
    public void create(User user) {


    }

    @Override
    public List<User> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<User> find(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();
            ps = conn.prepareStatement("SELECT * FROM user WHERE userID = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserID(rs.getInt("userID"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                return Optional.of(user);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByName(String name) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();
            ps = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserID(rs.getInt("userID"));  // Asegúrate de que el campo en la base de datos se llama userID
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                return Optional.of(user);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }
        return Optional.empty();
    }

    @Override
    public void update(User user) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(int id) {
        // TODO Auto-generated method stub

    }
}

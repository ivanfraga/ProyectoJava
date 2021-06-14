package dao;

import Modelo.User;
import db.IDBConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

import static db.DataBase.*;

public interface UserDAO extends IDBConnection {

    default ArrayList<User> read() {
        ArrayList<User> listaUser = new ArrayList<>();
        try (Connection connection = connectToDB()) {
            String query = "SELECT*FROM " + TUser;
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User listaUser1 = new User(rs.getInt(TUser_id),
                        rs.getString(TUser_NombleCompleto),
                        rs.getString(TUser_Pass));
                listaUser.add(listaUser1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaUser;
    }
}

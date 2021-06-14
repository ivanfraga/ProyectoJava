package dao;

import db.IDBConnection;
import viewController.viewLoginController;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static db.DataBase.*;

public class UserDAO_Login implements IDBConnection {
    public int login (String user, String password){
        Connection con = null;
        PreparedStatement pst;
        ResultSet rs;
        int state = -1;//estado de la consulta: -1: hubo error 0: no se encontro informacion 1: se encontro informacion

        try{

            con = connectToDB();

            if(con != null){

                String sql = "SELECT * FROM usuarios WHERE BINARY nombreCompleto=? AND pass=?";

                pst = con.prepareStatement(sql);
                pst.setString(1, user); //parametros de la funcion
                pst.setString(2, password);

                rs = pst.executeQuery();

                if(rs.next()){
                    if(user.equals(rs.getString(TUser_NombleCompleto))){
                     viewLoginController.IDUsuarioo =Integer.valueOf(rs.getString(TUser_id));
                     viewLoginController.NombreUsuario=rs.getString(TUser_NombleCompleto);
                     viewLoginController.PassUsuarioo=rs.getString(TUser_Pass);
                    }
                    state = 1;
                }else{
                    state = 0;
                }

            }else{
                JOptionPane.showMessageDialog(null, "Hubo un error en la conexion con la base de datos", "Error total", JOptionPane.ERROR_MESSAGE);
            }


        }catch(HeadlessException he)    {
            JOptionPane.showMessageDialog(null, "Hubo un error en la ejecucion, posibles errores:\n"
                    +he.getMessage());
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error**: "+e);
        }
        return state;
    }
    ///////////////////////////////////////////////////////////////////



}
//insert into usuarios (id, user, pass) values ("15", "kiokusanagi", aes_encrypt("kiokusanagi", "KEY"))
//INSERT into usuarios (id, user, pass) VALUES("16", "kiok9", aes_encrypt("1", "KEY"));





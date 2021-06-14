package Modelo;

import dao.UserDAO;
import dao.VuelosDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import static db.DataBase.*;
import static db.DataBase.PASS;

public class User extends Usuario implements UserDAO {
    public User() {
    }
    private String pass;

    public User(int id, String nombreCompleto, String pass) {
        super(id, nombreCompleto);
    }
    public static ArrayList<User> makeUserList() {

        User listaUser=new User(); //crear el constructor vacio
        return listaUser.read();
    }

    public void  UserSistem() {

        int id1;
        String name2,aer1,clas1,destino1,fech1,durac;
        Scanner sc = new Scanner(System.in);
        User UserInser=new User();
        System.out.println("Ingrese el id ");
        //id1= VuelosControl.validateUserResponseMenu(1,100);
        id1=sc.nextInt();
        System.out.println("Ingrese el nombre ");
        name2=sc.next();
        UserInser.setId(id1);
        UserInser.setNombreCompleto(name2);
        Connection connection=null;
        PreparedStatement ps =null;
        ResultSet rs= null;


        //modificar

        try {
            Class.forName("com.mysql.jdbc.Driver"); //Llamar al driver seleccionado coo paquete

            connection= DriverManager.getConnection(URL+DB,USER,PASS);
            // PreparedStatement instruccion =
            //Statement st =connection.createStatement();
            ps=connection.prepareStatement("INSERT INTO usuarios (id ,nombreCompleto, pass) VALUES(?,?, '1')");
            ps.setInt(1, UserInser.getId());
            ps.setString(2,UserInser.getNombreCompleto());

            ps.executeUpdate();

            if (connection!=null){
                System.out.println("Se establecio la conexio TABLA USUARI:) ");
            }
            System.out.println(" Se agrego los Datos con exito USER");

        }catch (Exception ex){//pongo la excepcion padre
            ex.printStackTrace();
        }finally {

            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }




    }

    /////////////////////////////////////eliminar vuelo
    public void eliminarUsuario(){
        PreparedStatement ps =null;
        ResultSet rs= null;
        Vuelos vuelInser = new Vuelos();
        //modificar
        User UserInser=new User();
        int id1;
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el id ");
        //id1= VuelosControl.validateUserResponseMenu(1,100);
        id1=sc.nextInt();
        UserInser.setId(id1);
        try(Connection connection = connectToDB()){
            PreparedStatement pst = connection.prepareStatement(
                    "DELETE FROM usuarios WHERE id=?");

            pst.setInt(1, UserInser.getId() );

            pst.executeUpdate();
            rs= pst.executeQuery();
        }catch(SQLException e) {
            System.err.println();
        }
    }
    ////////////////PASSWORD/////////////////////


    @Override
    public String getPass() {
        return pass;
    }

    @Override
    public void setPass(String pass) {
        this.pass = pass;
    }
}


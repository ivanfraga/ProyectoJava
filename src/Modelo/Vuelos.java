package Modelo;

import Controlador.VuelosControl;
import dao.VuelosDAO;
import db.DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static db.DataBase.*;

public class Vuelos  extends Usuario implements VuelosDAO {
    private String aerolinea ;
    private String clase ;
    private String destino ;
    private String fecha ;

    public Vuelos() {

    }

    public String getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(String aerolinea) {
        this.aerolinea = aerolinea;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    private String duracion;



    public Vuelos(int id, String nombreCompleto, String aerolinea, String clase, String destino, String fecha, String duracion) {
        super( id, nombreCompleto);
        this.aerolinea = aerolinea;
        this.clase = clase;
        this.destino = destino;
        this.fecha = fecha;
        this.duracion = duracion;
    }

    public Vuelos(Vuelos vuelos) {
        super();
    }

    public static ArrayList<Vuelos> makeVuelosList() {

        Vuelos listaVuelos=new Vuelos(); //crear el constructor vacio
        return listaVuelos.read();
    }


    public void   Conex(){
        Connection connection=null;
        PreparedStatement ps =null;
        ResultSet rs= null;
        Vuelos vuelInser = new Vuelos();
        //modificar
        User UserInser=new User();
        int id1;
        String name1,aer1,clas1,destino1,fech1,durac;
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el id ");
        //id1= VuelosControl.validateUserResponseMenu(1,100);
        id1=sc.nextInt();
        System.out.println("Ingrese el nombre ");
        name1=sc.next();
        System.out.println("Ingrese la aerolinea ");
        aer1= sc.next();
        System.out.println("Ingrese el clase ");
        clas1= sc.next();
        System.out.println("Ingrese la destino ");
        destino1= sc.next();
        System.out.println("Ingrese la fecha ");
        fech1= sc.next();
        durac="9 horas";

        //UserInser.setId(id1);
        vuelInser.setId(id1);
        //UserInser.setNombreCompleto(name1);
        vuelInser.setNombreCompleto(name1);
        vuelInser.setAerolinea(aer1);
        vuelInser.setClase(clas1);
        vuelInser.setDestino(destino1);;
        vuelInser.setFecha(fech1);
        vuelInser.setDuracion(durac);



        //modificado
        /*
        try {
            Class.forName("com.mysql.jdbc.Driver"); //Llamar al driver seleccionado coo paquete

            connection= DriverManager.getConnection(URL+DB,USER,PASS);
            // PreparedStatement instruccion =
            //Statement st =connection.createStatement();
            ps=connection.prepareStatement("INSERT INTO usuarios (id,nombreCompleto) VALUES(?,?)");
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
*/


        //


        try {
            Class.forName("com.mysql.jdbc.Driver"); //Llamar al driver seleccionado coo paquete

            connection= DriverManager.getConnection(URL+DB,USER,PASS);
           // PreparedStatement instruccion =
            //Statement st =connection.createStatement();
            ps=connection.prepareStatement("INSERT INTO vuelos (id,nombreCompleto,aerolinea,clase,destino,fecha,duracion) VALUES(?,?,?,?,?,?,?)");
            ps.setInt(1,vuelInser.getId());
            ps.setString(2,vuelInser.getNombreCompleto());

            ps.setString(3,vuelInser.getAerolinea());
            ps.setString(4,vuelInser.getClase());
            ps.setString(5,vuelInser.getDestino());
            ps.setString(6,vuelInser.getFecha());
            ps.setString(7,vuelInser.getDuracion());
            ps.executeUpdate();
            //ps.executeQuery();

            if (connection!=null){
                System.out.println("Se establecio la conexio :) ");
            }
            System.out.println(" Se agrego los Datos con exito");

        }catch (Exception ex){//pongo la excepcion padre
            ex.printStackTrace();
        }finally {

                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }

        //modificado

    }
    /////////////////////////////////////
    public void eliminarVuelos(){
        PreparedStatement ps =null;
        ResultSet rs= null;
        Vuelos vuelInser = new Vuelos();
        int id1;
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el ID del vuelo:  ");
        //id1= VuelosControl.validateUserResponseMenu(1,100);
        id1=sc.nextInt();
        vuelInser.setId(id1);
        try(Connection connection = connectToDB()){
            PreparedStatement pst = connection.prepareStatement(
                    "DELETE FROM vuelos WHERE id=?");

            pst.setInt(1, vuelInser.getId() );

            pst.executeUpdate();
            rs= pst.executeQuery();
        }catch(SQLException e) {
            System.err.println();
        }
    }
    /////////////////////////////////////

    /**
     * En esta seccion se lo separo para que pida Datos al usuario sobre el id para la tabla User
     */
    /*
    public void  UserSistem() {
        Connection connection=null;
        PreparedStatement ps =null;
        ResultSet rs= null;
        Vuelos vuelInser = new Vuelos();
        //modificar
        User UserInser=new User();
        try {
            Class.forName("com.mysql.jdbc.Driver"); //Llamar al driver seleccionado coo paquete

            connection= DriverManager.getConnection(URL+DB,USER,PASS);
            // PreparedStatement instruccion =
            //Statement st =connection.createStatement();
            ps=connection.prepareStatement("INSERT INTO usuarios (id,nombreCompleto) VALUES(?,?)");
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




    }*/
////////////////AQUI VAN LOS HASCODES//////////////////////////


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vuelos vuelos = (Vuelos) o;
        return Objects.equals(aerolinea, vuelos.aerolinea) && Objects.equals(clase, vuelos.clase) && Objects.equals(destino, vuelos.destino) && Objects.equals(fecha, vuelos.fecha) && Objects.equals(duracion, vuelos.duracion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aerolinea, clase, destino, fecha, duracion);
    }
}









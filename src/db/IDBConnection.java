package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static db.DataBase.*;
import static db.DataBase.PASS;

public interface IDBConnection {
    default Connection connectToDB(){
        Connection connection =null;
        //utilizar nuestra bloque try/catch/finaly
        try {
            Class.forName("com.mysql.jdbc.Driver"); //Llamar al driver seleccionado coo paquete
            connection= DriverManager.getConnection(URL+DB,USER,PASS);
            //quien ayuda a obtener la conexion  a partir  de una serie de relax (URL)
            //VERIFICCAR LA COONECCION
            if (connection!=null){
                System.out.println("Se establecio la conexio :) ");
            }

        }catch (Exception e){//pongo la excepcion padre
            e.printStackTrace();
        }finally {


        }return connection;








    }



}

package dao;

import Modelo.Vuelos;
import db.IDBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static db.DataBase.*;

public interface VuelosDAO  extends IDBConnection {
    default ArrayList<Vuelos> read(){
        ArrayList<Vuelos> listaVuelos = new ArrayList<>();
        try(Connection connection = connectToDB()){
            //traer datos de BDD para ello vamos a utilizar PreparedStatement
            String query = "SELECT*FROM "+ TLista;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            //que necesitamos llenar el objeto
            ResultSet rs = preparedStatement.executeQuery();
            //disparador
            while (rs.next()){

                Vuelos lisaVuelost1= new Vuelos(Integer.valueOf(rs.getString(id)),//por son int,rs.getString(nombreCompleto),
                        rs.getString(nombreCompleto),
                        rs.getString(aerolinea),
                        rs.getString(clase),rs.getString(destino), rs.getString(fecha),rs.getString(duracion) );
                listaVuelos.add(lisaVuelost1);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return listaVuelos ;
    }





}

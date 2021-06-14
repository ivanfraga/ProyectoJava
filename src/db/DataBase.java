package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DataBase {

    public static final String DB = "vuelos";
    public static final String URL = "jdbc:mysql://localhost:3306/";
    public static final String USER = "root";
    public static final String PASS = "";




    public static final String TLista = "vuelos";
    public static final String id = "id";
    public static final String nombreCompleto = "nombreCompleto";
    public static final String aerolinea = "aerolinea";
    public static final String clase = "clase";
    public static final String destino = "destino";
    public static final String fecha = "fecha";
    public static final String duracion = "duracion";


    //tabla usuarioa
    public static final String TUser = "usuarios";
    public static final String TUser_id = "id";
    public static final String TUser_NombleCompleto="nombreCompleto";
    public static final String TUser_Pass="pass";

    //constantes
    //Aerolineas
    public static final String Aero1 ="Charlotte";
    public static final String Aero2 ="LAN";
    public static final String Aero3 ="Avianca";
    // Constantes de Clase
    //
    public static final String Clase1 ="VIP";
    public static final String Clase2 ="Media";
    public static final String Clase3 ="Economica";

    //Destino
    public static final String Destino1 ="EEUU";
    public static final String Destino2 ="España";
    public static final String Destino3 ="Rusia";
    public static final String Destino4 ="Japòn";
    //Fechas
    public static final String Fecha1 ="15-03-2021";
    public static final String Fecha2 ="19-03-2021";
    public static final String Fecha3 ="27-03-2021";





}

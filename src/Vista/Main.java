package Vista;

import Modelo.User;
import Modelo.Vuelos;
import db.IDBConnection;
import viewController.UserViewInicioController;


import java.util.ArrayList;
import java.util.Scanner;

import static Modelo.User.makeUserList;
import static Modelo.Vuelos.makeVuelosList;

public class Main implements IDBConnection {

    public static void main(String[] args) {

        Menu.MenuPrincipal();
        //Vuelos vuelInser = new Vuelos();

      //Vuelos.Conex();
      //ostrarVuelos();
      //Vuelos n =new Vuelos();
      //Vuelos.Conex();
      //n.Conex();
       // mostrarVuelos();

    }
/*+
    public Connection getConnection() {
        return DB;
    }
    public void setConnection(Connection connection) {
        this.DB = connection;
    }
    //private Connection conexion ;
     private Connection DB;

*/
    public static ArrayList<Vuelos> listaVuelos = makeVuelosList();

    public static void mostrarVuelos()
    {
        Scanner sc = new Scanner(System.in);
        //int opc=0;
        boolean ingres=false;
        do {
            System.out.println();
            System.out.println(":: Base de Datos Vuelos ::");
            System.out.println();
            System.out.println("0. Regresar al Menu");



            for (int i = 0; i < listaVuelos.size(); i++) {


                System.out.println(" \n" +" ID PASAJERO: "+ "  " +listaVuelos.get(i).getId()+ "\n "+"NOMBRE COMPLETO: " +listaVuelos.get(i).getNombreCompleto()+"\n"+
                     " AEROLINEA:"+  listaVuelos.get(i).getAerolinea()+"\n "+"CLASE DE VUELO "+listaVuelos.get(i).getClase()+"\n "+"DESTINO :"+listaVuelos.get(i).getDestino()+ "\n"+" FECHA:  "+listaVuelos.get(i).getFecha()+"\n"+" DURACION: "+listaVuelos.get(i).getDuracion());
            }
            System.out.println("Ingresa la OPCION  0  Para Salir ");
            //opc=Integer.valueOf(sc.nextLine());

            System.out.println("Datos Completos ");



        } while(ingres==!false);
        System.out.println(" \n*************************************************Menu Principal********************************************\n");

    }


    ///

    static ArrayList<User> listaUser= makeUserList();

    public static void mostarUser()
    {
        Scanner sc = new Scanner(System.in);
        //int opc=0;
        boolean ingres=false;
        do {
            System.out.println();
            System.out.println(":: Base de Datos Vuelos ::");
            System.out.println();
            System.out.println("0. Regresar al Menu");



            for (int i = 0; i < listaUser.size(); i++) {


                System.out.println(" \n" +" ID PASAJERO: "+ "  " +listaUser.get(i).getId()+ "\n "+"NOMBRE COMPLETO: " +listaUser.get(i).getNombreCompleto()+"\n");

            }
            System.out.println("Ingresa la OPCION  0  Para Salir ");
            //opc=Integer.valueOf(sc.nextLine());

            System.out.println("Datos Completos ");



        } while(ingres==!false);
        System.out.println(" \n*************************************************Menu Principal********************************************\n");



    }





}

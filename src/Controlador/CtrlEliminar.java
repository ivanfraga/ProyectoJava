package Controlador;

import Modelo.User;
import Modelo.Vuelos;
import Vista.Menu;

import java.util.Scanner;

public class CtrlEliminar{

    public static void eliminarVuelos(){
        //char opc=' ';
        int opc = 0;

        int dato=0;

        Scanner sc = new Scanner(System.in);
        System.out.println("Desea eliminar seleccione (1): ");        opc = sc.nextInt();
//        opc = sc.next().charAt(0);
        if (opc == 1){
            Vuelos vuelos = new Vuelos();
            vuelos.eliminarVuelos();

        }
     /*   if(opc == 's' || opc == 'S') {
            Vuelos vuelos = new Vuelos();
            vuelos.eliminarVuelos();

      */
        else{
            System.out.println("No se ha realizado un cambio en la base de datos ..");
        Menu.MenuPrincipal();
        }
    }
    public static void eliminarUsuario(){
        //char opc=' ';
        int opc = 0;
        int dato=0;

        Scanner sc = new Scanner(System.in);
        System.out.println("Desea eliminar seleccione (1): ");
        opc = sc.nextInt();
//        opc = sc.next().charAt(0);
        if (opc == 1){

            CtrlVerificacionVuelos.verificacion_Vuelo();
            User u = new User();
            u.eliminarUsuario();


        }
     /*   if(opc == 's' || opc == 'S') {
            Vuelos vuelos = new Vuelos();
            vuelos.eliminarVuelos();

      */
        else{
            System.out.println("No se ha realizado un cambio en la base de datos ..");
            Menu.MenuPrincipal();
        }
    }





}

package Vista;

import Controlador.CtrlEliminar;
import Controlador.VuelosControl;
import Modelo.User;
import Modelo.Vuelos;

import java.sql.Connection;
import java.util.Scanner;

import static Vista.Main.mostarUser;
import static Vista.Main.mostrarVuelos;

public class Menu {
    public  static void MenuPrincipal (){
        int opcion;
        System.out.println("BIENVENIDO AL SISTEMA DE VUELOS ");
        System.out.println("Menu Principal");

        int selec;
        do{
            System.out.println("1.-Vuelos Agendados");
            System.out.println("2.-Modificar");
            System.out.println("3.-Agendar vuelos");
            System.out.println("4.-Ingresar usuario");
            System.out.println("5.-Eliminar usuario");
            System.out.println("6.-Eliminar vuelo");

            System.out.println("0.-Salir ");
            Scanner sc = new Scanner(System.in);
            System.out.println("INGRESE SU OPCION ...");
            selec= sc.nextInt();
            switch (selec){
                case 1:
                    mostrarVuelos();


                    break;
                case 2:
                    System.out.println("Hola");
                    mostarUser();


                    break;
                case 3:
                    Vuelos n =new Vuelos();

                    n.Conex();
                    //Vuelos.Conex();

                    break;
                case 4:
                    User o = new User();
                   o.UserSistem();

                    break;

                case 5:
                    /*
                    User u = new User();
                        u.eliminarUsuario();

                     */
                    CtrlEliminar.eliminarUsuario();


                    break;

                case 6:
                    /*
                    Vuelos vuelos = new Vuelos();

                    vuelos.eliminarVuelos();

                     */
                    CtrlEliminar.eliminarVuelos();

                    break;


            }


        }while (selec!=0);
    }



}

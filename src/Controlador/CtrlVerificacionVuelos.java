package Controlador;

import java.util.Scanner;

import static Vista.Main.listaVuelos;

public class CtrlVerificacionVuelos {

    public static void verificacion_Vuelo(){
        Scanner sc = new Scanner(System.in);
        int opci=1;
        boolean ingres=false;
        do {
            System.out.println();
            System.out.println(":: Base de Datos Vuelos ::");
            System.out.println();

            System.out.println("Ingresar el id del usuario que desea eliminar: ");
            opci=sc.nextInt();

            if(listaVuelos.size() !=0 ){

            for ( int i = 0; i < listaVuelos.size(); i++) {

                if(opci==listaVuelos.get(i).getId()) {


                    System.out.println(" \n" +" ID PASAJERO: "+ "  " +listaVuelos.get(i).getId()+ "\n "+"NOMBRE COMPLETO: " +listaVuelos.get(i).getNombreCompleto()+"\n"+
                            " AEROLINEA:"+  listaVuelos.get(i).getAerolinea()+"\n "+"CLASE DE VUELO "+listaVuelos.get(i).getClase()+"\n "+"DESTINO :"+listaVuelos.get(i).getDestino()+ "\n"+" FECHA:  "+listaVuelos.get(i).getFecha()+"\n"+" DURACION: "+listaVuelos.get(i).getDuracion());

                }

            }
            }else{
                System.out.println("No existen datos:");
            }

            System.out.println("Datos en pantalla ¿ahora desea eliminarlos (marque (1))?");

            int opcion = sc.nextInt();
            if(opcion == 1){
                CtrlEliminar.eliminarVuelos();
            }else{
                System.out.println("No se ha modificado cambios.");
            }


        } while(ingres==!false);
    }

}

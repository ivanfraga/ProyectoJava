package Modelo;

import java.util.ArrayList;

public class Usuario {
    private int id;
    private String nombreCompleto;
    private String pass;

    public Usuario() {
    }

    public Usuario(int id, String nombreCompleto) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
    }
/*
    public Usuario(String nombreCompleto, String pass) {
        this.nombreCompleto = nombreCompleto;
        this.pass = pass;
    }

 */

    public static ArrayList<Vuelos> makeVuelosList() {

        Vuelos listaVuelos=new Vuelos(); //crear el constructor vacio
        return listaVuelos.read();
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
}

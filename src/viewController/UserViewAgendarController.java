package viewController;

import Modelo.User;
import Modelo.Vuelos;
import dao.VuelosDAO;
import db.DataBase;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static Modelo.User.makeUserList;
import static Modelo.Usuario.makeVuelosList;
import static db.DataBase.*;
import static viewController.viewLoginController.IDUsuarioo;

public class UserViewAgendarController implements Initializable, VuelosDAO {
    @FXML
    private Button UserBtnInicio, UserBtnAgendar, UserBtnReporte, UserBtnSalir;

    @FXML
    void UserBtnAgendarA(ActionEvent event) {
    }

    @FXML
    void UserBtnInicioA(ActionEvent event) {
        loadStage("/view/UserViewInicio.fxml", event);
    }

    @FXML
    void UserBtnReporteA(ActionEvent event) {
        loadStage("/view/UserViewRegistros.fxml", event);
    }

    @FXML
    void UserBtnSalirA(ActionEvent event) {
        try {
            ((Node) (event.getSource())).getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("/view/viewLogin.fxml"));//bloque igual al del main
            Scene scene = new Scene(root);
            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.show();
        } catch (IOException e) {
            Logger.getLogger(viewLoginController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void loadStage(String url, Event event) {
        try {
            Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(url));//bloque igual al del main
            Scene scene = new Scene(root);
            stage.setTitle("Interfaz Usuario");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger.getLogger(viewLoginController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /////////////////////////////////////////Datos de la ventana//////////////////////////////////////////////
    @FXML
    private TextField txtNombre;
    @FXML
    private ComboBox<String> cmbUserAerolineaAgendar;
    @FXML
    private ComboBox<String> cmbUserClaseAgendar;
    @FXML
    private ComboBox<String> cmbUserDestinoAgendar;
    @FXML
    private ComboBox<String> cmbUserFechaAgendar;
    @FXML
    private ComboBox<String> cmbUserDuracionAgendar;
    @FXML
    private Button btnUserAgendar;

    @FXML
    void UserAgendar(ActionEvent event) {
        int i = 0;
        int k = validarId(i);
        System.out.println("k= ");
        if(k != 1){
            event.consume();
            connection = connectToDB();
        }else{
        disparar();
        JOptionPane.showMessageDialog(null, "Gracias por agendar.", "Agenda", JOptionPane.INFORMATION_MESSAGE);

        }
        }

    private Connection connection=null;
    private PreparedStatement ps=null;
    private ResultSet rs = null;
        @Override
        public void initialize (URL url, ResourceBundle resourceBundle){
            inicioCbx();
            connection = connectToDB();

        }

        public void inicioCbx(){
            //combo box Aerolinea/clase/destino/fecha/duracion
            ArrayList<String> listaero = new ArrayList<>();
            Collections.addAll(listaero, new String[]{"Charlotte", "LAN", "Avianca"});
            cmbUserAerolineaAgendar.getItems().addAll(listaero);
            //cboxEmail.getItems().add("Maria@gmail.com");

            ArrayList<String> listClas = new ArrayList<>();
            Collections.addAll(listClas, new String[]{"VIP: 640$", "Media: 390$", "Economica: 100$"});
            cmbUserClaseAgendar.getItems().addAll(listClas);

            ArrayList<String> listDest = new ArrayList<>();
            Collections.addAll(listDest, new String[]{"EEUU", "España", "Rusia", "Japon"});
            cmbUserDestinoAgendar.getItems().addAll(listDest);

            ArrayList<String> listFecha = new ArrayList<>();
            Collections.addAll(listFecha, new String[]{"15-03-2021", "19-03-2021", "27-03-2021", "11-04-2021"});
            cmbUserFechaAgendar.getItems().addAll(listFecha);

            ArrayList<String> listDur = new ArrayList<>();
            Collections.addAll(listDur, new String[]{"VIP: 1 Hora", "Media: 2 Horas", "Japon"});
            cmbUserDuracionAgendar.getItems().addAll(listDur);
        }




            private void disparar(){
                try {
                    connection = connectToDB();
                    ps = this.connection.prepareStatement("INSERT INTO vuelos (id,nombreCompleto,aerolinea,clase,destino,fecha,duracion) VALUES(?,?,?,?,?,?,?)");
                    ps.setInt(1, IDUsuarioo);
                    ps.setString(2, txtNombre.getText());
                    ps.setString(3, cmbUserAerolineaAgendar.getSelectionModel().getSelectedItem());
                    ps.setString(4, cmbUserClaseAgendar.getSelectionModel().getSelectedItem());
                    ps.setString(5, cmbUserDestinoAgendar.getSelectionModel().getSelectedItem());
                    ps.setString(6, cmbUserFechaAgendar.getSelectionModel().getSelectedItem());
                    ps.setString(7, cmbUserDuracionAgendar.getSelectionModel().getSelectedItem());
                    ps.executeUpdate();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
            }

            private int validarId(int state){
                try {
                    connection = connectToDB();
                    ps = this.connection.prepareStatement("SELECT id FROM vuelos WHERE id=?");
                    ps.setInt(1, IDUsuarioo);
                    rs = ps.executeQuery();
                    if(rs.next()) {
                        state = 0;
                        JOptionPane.showMessageDialog(null, "Ya tiene un Vuelo Agendado", "Agenda", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        state = 1;
                    }

                } catch (SQLException ex) {//pongo la excepcion padre
                    ex.printStackTrace();
                    //ex.initCause(ex);
                }
                finally {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                return state;
            }
    }


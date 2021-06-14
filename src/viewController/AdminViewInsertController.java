package viewController;

import Modelo.Vuelos;
import dao.UserDAO;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static db.DataBase.*;
import static viewController.viewLoginController.IDUsuarioo;

public class AdminViewInsertController implements Initializable, UserDAO {
    /////////////////////// BOTONES PANEL IZQUIERDO
    @FXML
    private Button AdminBtnInsertar, AdminBtnRegistro, AdminBtnEliminar, AdminBtnSalir;
    @FXML
    void AdminBtnEliminarA(ActionEvent event) {
        loadStage("/view/AdminViewCanceled.fxml", event);
    }

    @FXML
    void AdminBtnInsertarA(ActionEvent event) {
    }

    @FXML
    void AdminBtnRegistroA(ActionEvent event) {
        loadStage("/view/AdminViewRegistro.fxml", event);
    }

    @FXML
    void AdminBtnSalirA(ActionEvent event) {
        try{
            ((Node)(event.getSource())).getScene().getWindow().hide();
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
            stage.setTitle("Interfaz Administrador");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger.getLogger(viewLoginController.class.getName()).log(Level.SEVERE, null, e);
        }
    }/////////////////////////BOTONES PANEL IZQUIERDO//////////////////////////////
    @FXML
    private TextField txtId, txtNombre, txtContraseña;

    @FXML
    private Button btnAgendarUsuario;

    @FXML
    private TextField txtIDV;

    @FXML
    private Button btnBuscarIDV;

    @FXML
    private TextField txtNombreV;

    @FXML
    private Label verificacionIDLabel;

    @FXML
    private ComboBox<String> cbxAerolineaV;

    @FXML
    private ComboBox<String> cbxClaseV;

    @FXML
    private ComboBox<String> cbxDestinoV;

    @FXML
    private ComboBox<String> cbxFechaV;

    @FXML
    private ComboBox<String> cbxDuracionV;

    @FXML
    private Button btnAgendarVuelo;

    @FXML
    void AgendarUsuario(ActionEvent event) {
        int state = verificarIdUsuario();
        int state2;
        if(state == 5){
            JOptionPane.showMessageDialog(null, "El ID esta ocupado por otro tripulante", "*Registro Fallido*", JOptionPane.WARNING_MESSAGE);
            event.consume();
        }else{
            state2 = dispararUsuario();
            if(state2 == 0){
                JOptionPane.showMessageDialog(null, "Hubo un error con la conexion es posible que no este puesto todos los campos", "*Registro Fallido*", JOptionPane.WARNING_MESSAGE);
                event.consume();
            }else{
                JOptionPane.showMessageDialog(null, "Campos llenado con exito", "*Registro Exitoso*", JOptionPane.INFORMATION_MESSAGE);
            }

        }

    }

    @FXML
    void AgendarVuelo(ActionEvent event) {
        System.out.println("stateabsolute = "+stateAbsolute);
        int state_definitive = 0;
        if(stateAbsolute == 0){
            event.consume();
            stateAbsolute = 0;
        }else{
            //Object evt= event.getSource();

            //if(!evt.equals(cbxAerolineaV) && !evt.equals(cbxClaseV) && !evt.equals(cbxDestinoV) && !evt.equals(cbxFechaV)&& !evt.equals(cbxDestinoV)&& !evt.equals(cbxDuracionV)){
            //JOptionPane.showMessageDialog(null, "Un campo de seleccion no ha sido escogido vuelva ha escoger", "*Registro Fallido*", JOptionPane.WARNING_MESSAGE);
            state_definitive = verificarIdVuelos();
            System.out.println("state_defini");
            if(state_definitive == 5){
            dispararVuelo();
            JOptionPane.showMessageDialog(null, "Campos llenado con exito", "*Registro Exitoso*", JOptionPane.INFORMATION_MESSAGE);

            }else{
                JOptionPane.showMessageDialog(null, "El ID ya esta agendado para un vuelo", "*Registro Fallido*", JOptionPane.WARNING_MESSAGE);
                event.consume();
            }

            }//else{
            //}
        //}
        System.out.println("stateabsolute =* "+stateAbsolute);
    }
    @FXML
    void buscarIDV(ActionEvent event) {
        int state = 0;
        try {
            connection = connectToDB();
            ps = this.connection.prepareStatement("SELECT * FROM usuarios WHERE id=?");
            ps.setInt(1, Integer.parseInt(txtIDV.getText()));
            rs = ps.executeQuery();
            if(rs.next()) {
                txtNombreV.setText(rs.getString(nombreCompleto));
                state = 1;
                }
            else{
                JOptionPane.showMessageDialog(null, "No existe el usuario en el sistema", "*Registro vacio*", JOptionPane.INFORMATION_MESSAGE);
                state = 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally { try { connection.close();} catch (SQLException e) {e.printStackTrace(); } }
            stateAbsolute = state;
    }

    public void inicioCbx(){
        //combo box Aerolinea/clase/destino/fecha/duracion
        ArrayList<String> listaero = new ArrayList<>();
        Collections.addAll(listaero, new String[]{"Charlotte", "LAN", "Avianca"});
        cbxAerolineaV.getItems().addAll(listaero);
        //cboxEmail.getItems().add("Maria@gmail.com");

        ArrayList<String> listClas = new ArrayList<>();
        Collections.addAll(listClas, new String[]{"VIP: 640$", "Media: 390$", "Economica: 100$"});
        cbxClaseV.getItems().addAll(listClas);

        ArrayList<String> listDest = new ArrayList<>();
        Collections.addAll(listDest, new String[]{"EEUU", "España", "Rusia", "Japon"});
        cbxDestinoV.getItems().addAll(listDest);

        ArrayList<String> listFecha = new ArrayList<>();
        Collections.addAll(listFecha, new String[]{"15-03-2021", "19-03-2021", "27-03-2021", "11-04-2021"});
        cbxFechaV.getItems().addAll(listFecha);

        ArrayList<String> listDur = new ArrayList<>();
        Collections.addAll(listDur, new String[]{"VIP: 1 Hora", "Media: 2 Horas", "Japon"});
        cbxDuracionV.getItems().addAll(listDur);
    }

    private void dispararVuelo(){
        try {
            connection = connectToDB();
            ps = this.connection.prepareStatement("INSERT INTO vuelos (id,nombreCompleto,aerolinea,clase,destino,fecha,duracion) VALUES(?,?,?,?,?,?,?)");
            ps.setInt(1, Integer.parseInt(txtIDV.getText()));
            ps.setString(2, txtNombreV.getText());
            ps.setString(3, cbxAerolineaV.getSelectionModel().getSelectedItem());
            ps.setString(4, cbxClaseV.getSelectionModel().getSelectedItem());
            ps.setString(5, cbxDestinoV.getSelectionModel().getSelectedItem());
            ps.setString(6, cbxFechaV.getSelectionModel().getSelectedItem());
            ps.setString(7, cbxDuracionV.getSelectionModel().getSelectedItem());
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

    private int dispararUsuario(){
        int state = 0;
        try {
            connection = connectToDB();
            ps = this.connection.prepareStatement("INSERT INTO usuarios (id,nombreCompleto,pass) VALUES(?,?,?)");
            ps.setInt(1, Integer.parseInt(txtId.getText()));
            ps.setString(2, txtNombre.getText());
            ps.setString(3, txtContraseña.getText());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            state = 0;
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                state = 0;
            }
        }
            state = 1;
        return state;
    }

    private int verificarIdUsuario(){
  int state=0;
        try {
            connection = connectToDB();
            ps = this.connection.prepareStatement("SELECT id FROM usuarios WHERE id=?");
            ps.setInt(1, Integer.parseInt(txtId.getText()));
            rs = ps.executeQuery();
            if(rs.next()) {
            state = 5;
            }else{
                state = 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {try { connection.close();
            } catch (SQLException e) { e.printStackTrace(); }
        }

        return state;
    }

    private int verificarIdVuelos(){
        int state=0;
        try {
            connection = connectToDB();
            ps = this.connection.prepareStatement("SELECT * FROM vuelos WHERE id=?");
            ps.setInt(1, Integer.parseInt(txtIDV.getText()));
            rs = ps.executeQuery();
            if(rs.next()) {
                state = 0;
            }else{
                state = 5;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {try { connection.close();
        } catch (SQLException e) { e.printStackTrace(); }
        }

        return state;
    }


    private int stateAbsolute;
    private Connection connection=null;
    private PreparedStatement ps=null;
    private ResultSet rs = null;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
                inicioCbx();


    }
}

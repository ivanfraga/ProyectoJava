package viewController;

import Modelo.User;
import Modelo.Usuario;
import Modelo.Vuelos;
import dao.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static Modelo.User.makeUserList;
import static Modelo.Usuario.makeVuelosList;
import static db.DataBase.*;
import static viewController.viewLoginController.IDUsuarioo;

public class UserViewRegistrosController implements Initializable, UserDAO {
    ////////////////////////////////////////////////
    @FXML
    private Button UserBtnInicio, UserBtnAgendar, UserBtnReporte, UserBtnSalir;

    @FXML
    void UserBtnAgendarA(ActionEvent event) {
        loadStage("/view/UserViewAgendar.fxml", event);
    }

    @FXML
    void UserBtnInicioA(ActionEvent event) {
        loadStage("/view/UserViewInicio.fxml", event);
    }

    @FXML
    void UserBtnReporteA(ActionEvent event) {
    }

    @FXML
    void UserBtnSalirA(ActionEvent event) {
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
            stage.setTitle("Interfaz Usuario");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger.getLogger(viewLoginController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    ////////////////////////////////////////////////////
    @FXML
    private TableView<Vuelos> TableRegistroUsuario;

    @FXML
    private TableColumn columUserIdRegistro;

    @FXML
    private TableColumn columUserNombreRegistro;

    @FXML
    private TableColumn columUserAerolineaRegistro;

    @FXML
    private TableColumn columUserClaseRegistro;

    @FXML
    private TableColumn columUserDestinoRegistro;

    @FXML
    private TableColumn columUserFechaRegistro;

    @FXML
    private TableColumn columUserDuracionRegistro;

    private ObservableList<Vuelos> vuelos1;
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vuelos1 = FXCollections.observableArrayList();
        this.columUserIdRegistro.setCellValueFactory(new PropertyValueFactory("id"));//esta parte de aqui de ponele el string no entiendo como sabe si no hay ninguna variable que sea string puesta como dato en parentesis esto
        this.columUserNombreRegistro.setCellValueFactory(new PropertyValueFactory("nombreCompleto"));//pero de lo que si estoy seguro es de que tiene que ver con la calse estudiante que le bim_1 y le cogio
        this.columUserAerolineaRegistro.setCellValueFactory(new PropertyValueFactory("aerolinea"));
        this.columUserClaseRegistro.setCellValueFactory(new PropertyValueFactory("clase"));
        this.columUserDestinoRegistro.setCellValueFactory(new PropertyValueFactory("destino"));
        this.columUserFechaRegistro.setCellValueFactory(new PropertyValueFactory("fecha"));
        this.columUserDuracionRegistro.setCellValueFactory(new PropertyValueFactory("duracion"));

     //   ActionPlus();
        agregar();
    }

    public static ArrayList<Vuelos> listaVuelos = makeVuelosList();

    @FXML
    void ActionPlus() {
        try{

            for (int i = 0; i < listaVuelos.size(); i++) {
                if(listaVuelos.get(i).getId()==IDUsuarioo) {
                    Vuelos vuelos = new Vuelos(listaVuelos.get(i).getId(), listaVuelos.get(i).getNombreCompleto(),
                            listaVuelos.get(i).getAerolinea(), listaVuelos.get(i).getClase(), listaVuelos.get(i).getDestino(),
                            listaVuelos.get(i).getDestino(), listaVuelos.get(i).getDuracion());
            //if(!this.vuelos1.contains(vuelos)) {
            //}
                this.vuelos1.add(vuelos);
                this.TableRegistroUsuario.setItems(vuelos1);

            }
                }

        }catch(NumberFormatException ups){
            errorFormato(); ups.getStackTrace();
        }

    }

    public void agregar(){
        try {
            connection = connectToDB();
            ps = this.connection.prepareStatement("SELECT * FROM vuelos WHERE id=?");
            ps.setInt(1, IDUsuarioo);
            rs = ps.executeQuery();
            if(rs.next()) {
                JOptionPane.showMessageDialog(null, "Usted cuenta con un vuelo registrado", "*Registro existente*", JOptionPane.INFORMATION_MESSAGE);
                Vuelos vuelos = new Vuelos(Integer.valueOf(rs.getString(id)), rs.getString(nombreCompleto),
                        rs.getString(aerolinea), rs.getString(clase), rs.getString(destino),rs.getString(fecha), rs.getString(duracion));

                if(!this.vuelos1.contains(vuelos)) {
                    this.vuelos1.add(vuelos);
                    this.TableRegistroUsuario.setItems(vuelos1);
                }
            }else{
                JOptionPane.showMessageDialog(null, "No tiene vuelos registrados", "*Registro vacio*", JOptionPane.INFORMATION_MESSAGE);
            }
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



    //errores
    public void errorFormato(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("SuperError");
        alert.setContentText("Formato incorrecto");
        alert.showAndWait();
    }







}

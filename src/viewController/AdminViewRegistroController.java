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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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

public class AdminViewRegistroController implements Initializable, UserDAO {
    /////////////////////// BOTONES PANEL IZQUIERDO//////////////////////////////////
    @FXML
    private Button AdminBtnInsertar, AdminBtnRegistro, AdminBtnEliminar, AdminBtnSalir;
    @FXML
    void AdminBtnEliminarA(ActionEvent event) {
        loadStage("/view/AdminViewCanceled.fxml", event);
    }

    @FXML
    void AdminBtnInsertarA(ActionEvent event) {
        loadStage("/view/AdminViewInsert.fxml", event);
    }

    @FXML
    void AdminBtnRegistroA(ActionEvent event) {
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
    }/////////////////////////BOTONES PANEL IZQUIERDO///////////////////////////

    @FXML
    private TableView<Vuelos> TableFlight;
    @FXML
    private TableColumn columIdFlight, columNombreFlight, columAerolineaFlight, columClaseFlight, columDestinoFlight, columFechaFlight, columDuracionFlight;
    @FXML
    private TableView<Usuario> TableUser;
    @FXML
    private TableColumn columIdUser, columNombreUser;
    @FXML
    void clickMouseFlight(MouseEvent event) {

    }

    @FXML
    void clickMouseUser(MouseEvent event) {

    }


    private ObservableList<Vuelos> vuelos1;
    private ObservableList<Usuario> usuario1;
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vuelos1 = FXCollections.observableArrayList();
        this.columIdFlight.setCellValueFactory(new PropertyValueFactory("id"));
        this.columNombreFlight.setCellValueFactory(new PropertyValueFactory("nombreCompleto"));
        this.columAerolineaFlight.setCellValueFactory(new PropertyValueFactory("aerolinea"));
        this.columClaseFlight.setCellValueFactory(new PropertyValueFactory("clase"));
        this.columDestinoFlight.setCellValueFactory(new PropertyValueFactory("destino"));
        this.columFechaFlight.setCellValueFactory(new PropertyValueFactory("fecha"));
        this.columDuracionFlight.setCellValueFactory(new PropertyValueFactory("duracion"));

        //agregarVuelo();

        ///////////////////////Vuelos/////////////////////////
        usuario1 = FXCollections.observableArrayList();
        this.columIdUser.setCellValueFactory(new PropertyValueFactory("id"));
        this.columNombreUser.setCellValueFactory(new PropertyValueFactory("nombreCompleto"));

        //agregarUsuario();

        ActionPlusUsuario();
        ActionPlusVuelos();

    }



    public void agregarVuelo(){
        try { connection = connectToDB();
            ps = this.connection.prepareStatement("SELECT * FROM vuelos");
            rs = ps.executeQuery();
            if(rs.next()) {
                Vuelos vuelos = new Vuelos(Integer.valueOf(rs.getString(id)), rs.getString(nombreCompleto),
                        rs.getString(aerolinea), rs.getString(clase), rs.getString(destino),rs.getString(fecha), rs.getString(duracion));

                if(!this.vuelos1.contains(vuelos)) {
                    this.vuelos1.add(vuelos);
                    this.TableFlight.setItems(vuelos1);
                }
            }else{
                JOptionPane.showMessageDialog(null, "No tiene vuelos registrados", "*Registro vacio*", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally { try { connection.close(); } catch (SQLException e) { e.printStackTrace();
        }
        }
    }



    public void agregarUsuario(){
        try { connection = connectToDB();
            ps = this.connection.prepareStatement("SELECT * FROM usuarios");
            rs = ps.executeQuery();
            if(rs.next()) {
                Usuario usuarios = new Usuario(Integer.valueOf(rs.getString(id)), rs.getString(nombreCompleto));
                if(!this.usuario1.contains(usuarios)) {
                    this.usuario1.add(usuarios);
                    this.TableUser.setItems(usuario1);
                }
            }else{
                JOptionPane.showMessageDialog(null, "No tiene vuelos registrados", "*Registro vacio*", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally { try { connection.close(); } catch (SQLException e) { e.printStackTrace();
            }
        }
    }

    static ArrayList<User> listaUser= makeUserList();
    void ActionPlusUsuario() {
        ArrayList<User> listaUser= makeUserList();

        try{
            for (int i = 0; i < listaUser.size(); i++) {

                Usuario usuario = new Usuario(listaUser.get(i).getId(), listaUser.get(i).getNombreCompleto());
                if (!this.usuario1.contains(usuario)) {
                    this.usuario1.add(usuario);
                    this.TableUser.setItems(usuario1);
                }
            }
        }catch(NumberFormatException ups){
            ups.getStackTrace();
        }

    }
    public static ArrayList<Vuelos> listaVuelos = makeVuelosList();
    void ActionPlusVuelos() {
        ArrayList<Vuelos> listaVuelos = makeVuelosList();
        try{
            for (int i = 0; i < listaVuelos.size(); i++) {

                Vuelos vuelos= new Vuelos(listaVuelos.get(i).getId(), listaVuelos.get(i).getNombreCompleto(), listaVuelos.get(i).getAerolinea(),
                        listaVuelos.get(i).getClase(), listaVuelos.get(i).getDestino(), listaVuelos.get(i).getFecha(), listaVuelos.get(i).getDuracion());
                if (!this.vuelos1.contains(vuelos)) {
                    this.vuelos1.add(vuelos);
                    this.TableFlight.setItems(vuelos1);
                }
            }
        }catch(NumberFormatException ups){
            ups.getStackTrace();
        }
    }


}

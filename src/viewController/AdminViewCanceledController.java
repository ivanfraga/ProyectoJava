package viewController;
import Modelo.Usuario;
import Modelo.Vuelos;
import dao.VuelosDAO;
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
import java.util.logging.Level;
import java.util.logging.Logger;

import static Modelo.Usuario.makeVuelosList;
import static db.DataBase.*;

public class AdminViewCanceledController implements Initializable, VuelosDAO {
    /////////////////////// BOTONES PANEL IZQUIERDO
    @FXML
    private Button AdminBtnInsertar, AdminBtnRegistro, AdminBtnEliminar, AdminBtnSalir;

    @FXML
    void AdminBtnEliminarA(ActionEvent event) {
    }

    @FXML
    void AdminBtnInsertarA(ActionEvent event) {
        loadStage("/view/AdminViewInsert.fxml", event);
    }

    @FXML
    void AdminBtnRegistroA(ActionEvent event) {
        loadStage("/view/AdminViewRegistro.fxml", event);
    }

    @FXML
    void AdminBtnSalirA(ActionEvent event) {
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
            stage.setTitle("Interfaz Administrador");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger.getLogger(viewLoginController.class.getName()).log(Level.SEVERE, null, e);
        }
    }/////////////////////////BOTONES PANEL IZQUIERDO///////////////////////////////////
    @FXML
    private TextField txtIDVuelo, txtIDUsuario;
    @FXML
    private Button btnBuscarVuelo, btnEliminarVuelo, btnBuscarUsuario, btnEliminarUsuario;
    @FXML
    private TableView<Vuelos> TablaVuelo;
    @FXML
    private TableColumn tblIDV, tblNombreV, tblAerolineaV, tblClaseV, tblDestinoV, tblFechaV, tblDuracionV;
    @FXML
    private TableView<Usuario> TableUsuario;
    @FXML
    private TableColumn tblIDU, tblNombreU;


    @FXML
    void BuscarVuelo(ActionEvent event) {
        buscarVueloGeneral(txtIDVuelo.getText());
    }
    @FXML
    void BuscarUsuario(ActionEvent event) {
        buscarVueloGeneral(txtIDUsuario.getText());
        try {
            connection = connectToDB();
            ps = this.connection.prepareStatement("SELECT * FROM usuarios WHERE id=?");
            ps.setInt(1, Integer.parseInt(txtIDUsuario.getText()));
            rs = ps.executeQuery();
            if (rs.next()) {
                Usuario user = new Usuario(Integer.valueOf(rs.getString(id)), rs.getString(nombreCompleto));
                if (!this.usuarios1.contains(user)) {
                    this.usuarios1.add(user);
                    this.TableUsuario.setItems(usuarios1);
                }
            } else {
                JOptionPane.showMessageDialog(null, "El ID no es un usuario en el sistema", "*Registro vacio*", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try { connection.close();
            } catch (SQLException e) { e.printStackTrace(); } } }


    public void buscarVueloGeneral(String txtID){
        try {
            connection = connectToDB();
            ps = this.connection.prepareStatement("SELECT * FROM vuelos WHERE id=?");
            ps.setInt(1, Integer.parseInt(txtID));
            rs = ps.executeQuery();
            if (rs.next()) {
                Vuelos vuelos = new Vuelos(Integer.valueOf(rs.getString(id)), rs.getString(nombreCompleto),
                        rs.getString(aerolinea), rs.getString(clase), rs.getString(destino), rs.getString(fecha), rs.getString(duracion));
                if (!this.vuelos1.contains(vuelos)) {
                    this.vuelos1.add(vuelos);
                    this.TablaVuelo.setItems(vuelos1);
                }
            } else {
                JOptionPane.showMessageDialog(null, "El ID no cuenta con vuelo", "*Registro vacio*", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally { try { connection.close(); } catch (SQLException e) {
                e.printStackTrace(); } } }


    public int iteradorEliminarUsuario;
    @FXML
    void clickUsuario(MouseEvent event) {
        Usuario userRanger = this.TableUsuario.getSelectionModel().getSelectedItem();
        if (userRanger != null) {
            this.iteradorEliminarUsuario = userRanger.getId();
        }else{ JOptionPane.showMessageDialog(null, "Selecciona un Usuario", "*Informacion*", JOptionPane.INFORMATION_MESSAGE); }

    }

    public int iteradorEliminarVuelo;
    @FXML
    void clickVuelo(MouseEvent event) {
        Vuelos vuelofligh = this.TablaVuelo.getSelectionModel().getSelectedItem();
        if (vuelofligh != null) {
            this.iteradorEliminarVuelo = vuelofligh.getId();
        }else{ JOptionPane.showMessageDialog(null, "Selecciona un Vuelo", "*Informacion*", JOptionPane.INFORMATION_MESSAGE); }
    }
    @FXML
    void EliminarUsuario(ActionEvent event) {
            int k = busquedaVueloParcial(iteradorEliminarUsuario);
            if(k==0){
                int valor_3 = JOptionPane.showConfirmDialog(null,"¿Desea eliminar el usuario", "Informacion", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if(valor_3== JOptionPane.YES_OPTION){
                    eliminarUsuario(iteradorEliminarUsuario);
                    eliminarTablaUsuario();
                }
            }else if(k==1){
                int valor = JOptionPane.showConfirmDialog(null,"El usuario cuenta con un Vuelo\n"+"¿Esta seguro de cancelar un vuelo?", "Advertencia", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                if(valor==JOptionPane.YES_OPTION) {
                    eliminarVueloGeneral(iteradorEliminarUsuario);
                    JOptionPane.showMessageDialog(null, "Gracias por confirmar", "Realizado", JOptionPane.INFORMATION_MESSAGE);
                    eliminarTablaVuelos();
                    int segundoValor = JOptionPane.showConfirmDialog(null,"¿Desea eliminar el usuario", "Informacion", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    if(segundoValor== JOptionPane.YES_OPTION){
                        eliminarUsuario(iteradorEliminarUsuario);
                        eliminarTablaUsuario();
                    }
                }

            }else{
                event.consume();
            }
    }
     public int busquedaVueloParcial(int txtID){
        int state =0;
         try {
             connection = connectToDB();
             ps = this.connection.prepareStatement("SELECT * FROM vuelos WHERE id=?");
             ps.setInt(1, txtID);
             rs = ps.executeQuery();
             if (rs.next()) {
                 state = 1;
             } else {
                 JOptionPane.showMessageDialog(null, "El ID no cuenta con vuelo", "*Registro vacio*", JOptionPane.INFORMATION_MESSAGE);
                 state = 0;
             }
         } catch (SQLException ex) {
             ex.printStackTrace();
         } finally {
             try {
                 connection.close();
             } catch (SQLException e) {
                 e.printStackTrace();
             }
         }
         return state;
     }

     public void eliminarUsuario(int iterador){
         try {
         connection = connectToDB();
             ps = connection.prepareStatement("DELETE FROM usuarios WHERE id=?");
             ps.setInt(1, iterador);

             ps.executeUpdate();
             rs = ps.executeQuery();
         } catch (SQLException e) {
             System.err.println();
         } finally {
             try {
                 connection.close();
             } catch (SQLException e) {
                 e.printStackTrace();
             }
         }
         eliminarTablaUsuario();

     }



    /////////////buscar el vuelo despues el usuario/////////////
    @FXML
    public void EliminarVuelo(ActionEvent event) {
        eliminarVueloGeneral(iteradorEliminarVuelo);

    }
    public void eliminarVueloGeneral(int iterador){
        try {
        connection = connectToDB();
            ps = connection.prepareStatement("DELETE FROM vuelos WHERE id=?");
            ps.setInt(1, iterador);

            ps.executeUpdate();
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.err.println();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
eliminarTablaVuelos();
    }

    public void eliminarTablaVuelos() {
        Vuelos vuelo = this.TablaVuelo.getSelectionModel().getSelectedItem();
        if (vuelo == null) {
        } else {//accion de eliminar
            this.vuelos1.remove(vuelo);
            this.TablaVuelo.refresh();
        }
    }
    public void eliminarDosTablas(){
        //aqui tengo que buscarlo
}

    public void eliminarTablaUsuario() {
        Usuario user = this.TableUsuario.getSelectionModel().getSelectedItem();
        if (user == null) {

        } else {//accion de eliminar
            this.usuarios1.remove(user);
            this.vuelos1.remove(user);
            this.TableUsuario.refresh();
            this.TablaVuelo.refresh();
        }
    }

    private ObservableList<Vuelos> vuelos1;
    private ObservableList<Usuario> usuarios1;
    public static ArrayList<Vuelos> listaVuelos = makeVuelosList();
    private Connection connection=null;
    private PreparedStatement ps=null;
    private ResultSet rs = null;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ///////////////////PARA TABLA VUELOS///////////////////////////
        vuelos1 = FXCollections.observableArrayList();
        this.tblIDV.setCellValueFactory(new PropertyValueFactory("id"));
        this.tblNombreV.setCellValueFactory(new PropertyValueFactory("nombreCompleto"));
        this.tblAerolineaV.setCellValueFactory(new PropertyValueFactory("aerolinea"));
        this.tblClaseV.setCellValueFactory(new PropertyValueFactory("clase"));
        this.tblDestinoV.setCellValueFactory(new PropertyValueFactory("destino"));
        this.tblFechaV.setCellValueFactory(new PropertyValueFactory("fecha"));
        this.tblDuracionV.setCellValueFactory(new PropertyValueFactory("duracion"));
        ////////////////////PARA TABLA USUARIOS////////////////////////
        usuarios1 = FXCollections.observableArrayList();
        this.tblIDU.setCellValueFactory(new PropertyValueFactory("id"));
        this.tblNombreU.setCellValueFactory(new PropertyValueFactory("nombreCompleto"));


    }
}


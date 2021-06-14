package viewController;

import Modelo.User;
import Modelo.Usuario;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static Modelo.User.makeUserList;
import static Vista.Main.mostarUser;
import static viewController.viewLoginController.IDUsuarioo;
import static viewController.viewLoginController.PassUsuarioo;

public class UserViewInicioController extends Thread implements Initializable {
    @FXML
    private Button UserBtnInicio, UserBtnAgendar, UserBtnReporte, UserBtnSalir;
    @FXML
    void UserBtnAgendarA(ActionEvent event) {
        loadStage("/view/UserViewAgendar.fxml", event);
    }
    @FXML
    void UserBtnInicioA(ActionEvent event) {
    }
    @FXML
    void UserBtnReporteA(ActionEvent event) {
        loadStage("/view/UserViewRegistros.fxml", event);
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
//////////////////////////////////////////////////////////////
    @FXML
    private TextField txtNombreInicio, txtContraseñaInicio;
    private String user, pass;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mostarUser1();
    }

    static ArrayList<User> listaUser= makeUserList();
    public void mostarUser1()
    {
        boolean ingres=false;
        do {
            for (int i = 0; i < listaUser.size(); i++) {
                if (listaUser.get(i).getId() == IDUsuarioo) {
                    txtNombreInicio.setText(listaUser.get(i).getNombreCompleto());
                    txtContraseñaInicio.setText(PassUsuarioo);
                }
            }
        } while(ingres==!false);
        System.out.println(" \n*************************************************Menu Principal********************************************\n");
    }

}
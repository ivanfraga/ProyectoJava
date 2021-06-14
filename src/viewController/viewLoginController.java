package viewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import Modelo.Usuario;
import dao.UserDAO_Login;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import javax.swing.JOptionPane;

public class viewLoginController implements Initializable {
    private UserDAO_Login model= new UserDAO_Login();
    public static int IDUsuarioo;
    public static  String NombreUsuario;
    public static String PassUsuarioo;
    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnLogin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void eventKey(KeyEvent event) {
        Object evt = event.getSource();//para ver en que nodo estamos posicionados

        if (evt.equals(txtUsuario)){//estamos viendo en el nodo
            if(event.getCharacter().equals(" ")){//para impedir el ingreso del espacio
                event.consume();
            }
        }else if (evt.equals(txtPassword)){
            if(event.getCharacter().equals(" ")){
                event.consume();
            }

        }
    }
                public String saberUsuario = "";

    public String getSaberUsuario() {
        return saberUsuario;
    }

    public void setSaberUsuario(String saberUsuario) {
        this.saberUsuario = saberUsuario;
    }

    @FXML
    private void eventAction(ActionEvent event) {


        Object nodos = event.getSource();//para evaluar en que nodos se esta inicializando

        if (nodos.equals(btnLogin)){

            if(!txtUsuario.getText().isEmpty() && !txtPassword.getText().isEmpty()){//para ver si los campos tienen informacion

                String user = txtUsuario.getText();
                String pass = txtPassword.getText();

                int state = model.login(user, pass);

                if(state != -1){

                    if(state == 1){
                        JOptionPane.showMessageDialog(null, "Datos correctos se pudo ingresar al sistema");
                            loadStage("/view/viewEntrada.fxml", event, "AeroQuito");


                    }else{
                        JOptionPane.showMessageDialog(null, "Error al iniciar sesión datos de acceso incorrectos",
                                "Advertencia", JOptionPane.WARNING_MESSAGE);

                    }
                }





            }else{
                JOptionPane.showMessageDialog(null, "Error al iniciar sesion, datos de acceso incorrectos",
                        "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);

            }

        }
    }

    private void loadStage(String url, Event event, String v){
        try{
            ((Node)(event.getSource())).getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource(url));
            Scene scene = new Scene(root);
            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.setTitle(v);
            newStage.show();
        }catch(IOException e){
            Logger.getLogger(viewLoginController.class.getName()).log(Level.SEVERE, null, e);
        }
    }



}

package Vista;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;

public class Principal extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private String url="";
    public void init(){

        int option = Integer.parseInt(JOptionPane.showInputDialog("INTERFACES GRÁFICAS\n"
                + "1- Principal\n"
                + "2- Login Administrador\n"
                + "3- Login Usuario\n"));

        switch (option){

            case 1:
                Menu.MenuPrincipal();;break;

            case 2:
                url = "/view/viewLoginAdministrador.fxml";break;

            case 3:
                url = "/view/viewLogin.fxml";break;

            default:
                Menu.MenuPrincipal();;break;

        }

    }

    @Override
    public void start(Stage primaryStage) {

        if (url != "") {
            try {
                Parent root = FXMLLoader.load(getClass().getResource(url));


                Scene scene = new Scene(root);

                primaryStage.setTitle("Flights");
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else  {
            System.out.println("Gracias por utilizar el sistema de vuelos. :)");
        }


    }




    }





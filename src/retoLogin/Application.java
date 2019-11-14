/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retoLogin;

import java.io.IOException;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import retoLogin.control.Client;
import retoLogin.control.ClientFactory;
import retoLogin.control.ClientImplementation;
import retoLogin.view.FXMLDocumentControllerLogin;

/**
 * The main class of the application
 * @author Jon Calvo Gaminde
 */
public class Application extends javafx.application.Application {
    /**
     * The method that prepares the JavafX windows
     * @param stage The stage to have the windows
     * @throws IOException Exception during the load of the login FXML file
     */
    /*MODIFICACIÓN DIN 13/11/2019*/
    private Client client = ClientFactory.getClient();
    @Override
    public void start(Stage stage) throws IOException  {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/login.fxml"));
            Parent root = (Parent) loader.load();
            FXMLDocumentControllerLogin viewController = loader.getController();
            /*MODIFICACIÓN DIN 13/11/2019*/
            viewController.setClient(client);
            viewController.setStage(stage);
            viewController.initStage(root);
    }

    /**
     * The method that starts the application
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}

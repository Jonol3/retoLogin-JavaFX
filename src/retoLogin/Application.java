/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retoLogin;

import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import retoLogin.view.FXMLDocumentControllerLogin;

/**
 *
 * @author 2dam
 */
public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/login.fxml"));
        Parent root = (Parent) loader.load();
        FXMLDocumentControllerLogin viewController = loader.getController();
        viewController.setStage(stage);
        viewController.initStage(root);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}

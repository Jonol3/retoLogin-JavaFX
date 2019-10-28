/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retoLogin.view;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import retoLogin.User;

/**
 * FXML Controller class
 *
 * @author Jon
 */
public class FXMLDocumentControllerSignOut {
    private User user;
    private Stage stage;
    @FXML
    private Label lbGreeting;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Initializes the controller class.
     */
    public void initStage(Parent root) {
        stage.setOnShowing(this::handleWindowShowing);
        Scene scene = new Scene (root);
        stage.setScene(scene);
        stage.show();
    }    
    
    public void handleWindowShowing(WindowEvent event) {
        lbGreeting.setText("Welcome, " + user.getFullName());
    }
    
    @FXML
    public void handleButtonLogOut(ActionEvent event) {
        //TODO
    }
    @FXML
    public void handleButtonClose(ActionEvent event) {
        Platform.exit();
    }
    @FXML
    public void handleButtonWorks(ActionEvent event) {
        //TODO
    }
    @FXML
    public void handleButtonAbout(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION, "Version 0.1\nMade by Jon, Unai and Daira.");
        alert.setHeaderText("About");
        Optional<ButtonType> okButton = alert.showAndWait();
        if (okButton.isPresent() && okButton.get() == ButtonType.OK) {
            alert.close();
        }
    }
}


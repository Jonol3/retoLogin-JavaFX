/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retoLogin.view;

import java.util.Optional;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
        stage.setTitle("Application");
        stage.setOnShowing(this::handleWindowShowing);
        stage.setOnCloseRequest(this::handleWindowClosing);
        Scene scene = new Scene (root);
        stage.setScene(scene);
        stage.show();
    }    
    
    public void handleWindowShowing(WindowEvent event) {
        stage.setMaximized(true);
        lbGreeting.setText("Welcome, " + user.getFullName());
    }
    
    public void handleWindowClosing(WindowEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION, "");
        alert.setTitle("Log out");
        alert.setHeaderText("Log out and return to login?");
        Optional<ButtonType> okButton = alert.showAndWait();
        if (okButton.isPresent() && okButton.get() == ButtonType.CANCEL) {    
            event.consume();
        }
    }
    
    @FXML
    public void handleButtonLogOut(ActionEvent event) {
        stage.close();
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
        Alert alert = new Alert(AlertType.INFORMATION, "Made by Jon, Unai and Daira.");
        alert.setTitle("About");
        alert.setHeaderText("Version 0.1");
        Optional<ButtonType> okButton = alert.showAndWait();
        if (okButton.isPresent() && okButton.get() == ButtonType.OK) {
            alert.close();
        }
    }
}


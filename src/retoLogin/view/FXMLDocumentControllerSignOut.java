/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retoLogin.view;

import java.io.IOException;
import java.util.Optional;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import retoLogin.User;

/**
 * FXML Controller class for the Sign Out window
 * @author Jon Calvo Gaminde
 */
public class FXMLDocumentControllerSignOut {
    private User user;
    private Stage stage;
    @FXML
    private Label lbGreeting;
    @FXML
    private Button btLogOut;

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
     * Initializes the stage
     * @param root The Parent of the scene
     */
    public void initStage(Parent root) {
        stage.setTitle("Application");
        stage.setOnShowing(this::handleWindowShowing);
        stage.setOnCloseRequest(this::handleWindowClosing);
        Scene scene = new Scene (root);
        stage.setScene(scene);
        stage.show();
    }   
    
    /**
     * This method handle the actions when the window shows
     * @param event Object of type WindowEvent
     */
    public void handleWindowShowing(WindowEvent event) {
        stage.setMaximized(true);
        lbGreeting.setText("Welcome, " + user.getFullName());
        
        btLogOut.setMnemonicParsing(true);
        btLogOut.setText("_Log out");
    }
    /**
     * This method handle the actions when the user click the close button of the window
     * @param event Object of type WindowEvent
     */
    public void handleWindowClosing(WindowEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION, "");
        alert.setTitle("Log out");
        alert.setHeaderText("Log out and return to login?");
        Optional<ButtonType> okButton = alert.showAndWait();
        if (okButton.isPresent() && okButton.get() == ButtonType.CANCEL) {    
            event.consume();
        }
    }
    
    /**
     * This method handle the actions when the user click the LogOut button
     * @param event The clicking event
     */
    @FXML
    private void handleButtonLogOut(ActionEvent event) {
        stage.close();
    }
    
    /**
     * This method handle the actions when the user click the close button
     * @param event The clicking event
     */
    @FXML
    private void handleButtonClose(ActionEvent event) {
        Platform.exit();
    }
    
    /**
     * This method handle the actions when the user click the how-it-works button
     * @param event The clicking event
     */
    @FXML
    private void handleButtonWorks(ActionEvent event) {
        try{
            Parent root = null;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("help.fxml"));
            root = (Parent) loader.load();
            FXMLDocumentControllerHelp viewController = loader.getController();
            viewController.initStage(root);
        } catch (IOException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("An error has ocurred.");

            alert.showAndWait();
       }
    }
    
    /**
     * This method handle the actions when the user click the about button
     * @param event The clicking event
     */
    @FXML
    private void handleButtonAbout(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION, "Made by Jon Calvo Gaminde, Unai Pérez Sánchez and Daira Eguzkiza Lamelas.");
        alert.setTitle("About");
        alert.setHeaderText("Version 1.0");
        Optional<ButtonType> okButton = alert.showAndWait();
        if (okButton.isPresent() && okButton.get() == ButtonType.OK) {
            alert.close();
        }
    }
}


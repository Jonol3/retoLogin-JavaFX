/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retoLogin.view;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;
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
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import retoLogin.User;

/**
 * FXML Controller class for the Sign Out window
 * @author Jon Calvo Gaminde
 */
public class FXMLDocumentControllerSignOut {
    /*MODIFICACION DIN 13/11/2019*/
    /**
     * The Logger to output messages.
     */
    private Logger LOGGER = Logger.getLogger("retoLogin.view.FXMLDocumentControllerSignOut");
    /**
     * The logged user, received from login or signup.
     */
    private User user;
    /**
     * The Stage where the view shows.
     */
    private Stage stage;
    /**
     * The label that cotains the greeting.
     */
    @FXML
    private Label lbGreeting;
    /**
     * The button to return to the login.
     */
    @FXML
    private Button btLogOut;
    /**
     * The option File of the top menu.
     */
    @FXML
    private Menu mntFile;
    /**
     * The option Help of the top menu.
     */
    @FXML
    private Menu mntHelp;
    /**
     * The menu item Close of the top menu, located under File option.
     */
    @FXML
    private MenuItem mnddClose;
    /**
     * The menu item How it Works of the top menu, located under Help option.
     */
    @FXML
    private MenuItem mnddWorks;
    /**
     * The menu item About of the top menu, located under Help option.
     */
    @FXML
    private MenuItem mnddAbout;

    /**
     * The constructor for the LogOut Window controller.
     */
    public FXMLDocumentControllerSignOut() {
    }

    /**
     * The setter of the stage for the SignOut.
     * @param stage The stage that will be setted.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    /**
     * The getter of the user from the SignOut.
     * @return user The user of the SignOut.
     */
    public User getUser() {
        return user;
    }

    /**
     * The setter of the user for the SignOut.
     * @param user The user that will be setted.
     */
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
        /*MODIFICACION DIN 13/11/2019*/
        stage.setMaximized(true);
        lbGreeting.setText("Welcome, " + user.getFullName());
        
        mnddWorks.setAccelerator(KeyCombination.keyCombination("F1"));
        mnddClose.setAccelerator(KeyCombination.keyCombination(KeyCombination.ALT_DOWN+"+C"));
        mnddAbout.setAccelerator(KeyCombination.keyCombination(KeyCombination.ALT_DOWN+"+A"));
        
        btLogOut.setMnemonicParsing(true);
        btLogOut.setText("_Log out");
        
        mntFile.setMnemonicParsing(true);
        mntFile.setText("_File");
        
        mntHelp.setMnemonicParsing(true);
        mntHelp.setText("_Help");
        
        
    }
    /**
     * This method handle the actions when the user click the close button of the window
     * @param event Object of type WindowEvent
     */
    public void handleWindowClosing(WindowEvent event) {
        /*MODIFICACION DIN 13/11/2019*/
        Alert alert = new Alert(AlertType.CONFIRMATION, "");
        alert.setTitle("Close");
        alert.setHeaderText("Are you sure that you want to close the application?");
        Optional<ButtonType> okButton = alert.showAndWait();
        if (okButton.isPresent() && okButton.get() == ButtonType.CANCEL) {    
            event.consume();
        } else if (okButton.isPresent() && okButton.get() == ButtonType.OK) {
            LOGGER.info("Closing the application.");
            Platform.exit();
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
        /*MODIFICACION DIN 13/11/2019*/
        stage.close();
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


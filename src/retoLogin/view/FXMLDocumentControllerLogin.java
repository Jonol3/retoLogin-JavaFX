/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retoLogin.view;

import java.io.IOException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import retoLogin.User;
import retoLogin.control.Client;
import retoLogin.control.ClientFactory;
import retoLogin.exceptions.*;

/**
 * FXML Controller class for the Login window
 * @author Daira Eguzkiza Lamelas, Jon Calvo Gaminde
 */
public class FXMLDocumentControllerLogin {
    
    
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnSignUp;
    @FXML
    private TextField txtFieldLogin;
    @FXML
    private PasswordField txtFieldPassword;
    
    private Stage stage;
    private Client client;

    User user = new User();

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    public void setClient(Client client) {
        this.client = client;
    }

    
    
    /**
     * Initializes the stage
     * @param root The Parent of the scene
     */
    public void initStage(Parent root) {
        stage.setTitle("Login");
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
        stage.setResizable(false);
        addTextLimiter(txtFieldLogin, 30);
        addTextLimiter(txtFieldPassword, 50);
        
        btnLogin.setMnemonicParsing(true);
        btnLogin.setText("_Log in");
        btnSignUp.setMnemonicParsing(true);
        btnSignUp.setText("_Sign Up");
    }
    
    /**
     * This method handle the actions when the user click the close button of the window
     * @param event Object of type WindowEvent
     */
    public void handleWindowClosing(WindowEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION, "");
        alert.setTitle("Close");
        alert.setHeaderText("Are you sure that you want to close the application?");
        Optional<ButtonType> okButton = alert.showAndWait();
        if (okButton.isPresent() && okButton.get() == ButtonType.CANCEL) {    
            event.consume();
        }
    }

    /**
     * This will try to log in the user. Controlls wether the data entered is
     * valid or not.
     * @param event The clicking event
     */
    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String login = txtFieldLogin.getText();
        String passwd = txtFieldPassword.getText();
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(login);
        boolean specialChars = m.find();
        //The limiter should do its job, but this code double checks
        if (login.length() > 30 || specialChars) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Invalid username.");
            alert.setHeaderText(null);
            alert.setContentText("You must enter a valid username.");

            alert.showAndWait();
        } else if (login.length() < 1 || passwd.length() < 1) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Empty username/password.");
            alert.setHeaderText(null);
            alert.setContentText("You must enter a username and a password.");

            alert.showAndWait();
        } else {
            try {
                user.setLogin(login);
                user.setPassword(passwd);

                
                user = client.loginUser(user);

                Parent root;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("signOut.fxml"));
                root = (Parent) loader.load();
                FXMLDocumentControllerSignOut viewController = loader.getController();
                viewController.setUser(user);
                Stage secondStage = new Stage();
                secondStage.initModality(Modality.APPLICATION_MODAL);
                viewController.setStage(secondStage);
                viewController.initStage(root);
                   
                
                txtFieldLogin.clear();
                txtFieldPassword.clear();
            
                
            } catch (BadLoginException e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("That user does not exist.");

                alert.showAndWait();
            } catch (LoginException e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Unexpected error happened.");

                alert.showAndWait();
            } catch (NoThreadAvailableException e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("The server is overloaded, try again later.");

                alert.showAndWait();
            } catch (BadPasswordException e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("The password you have entered is not correct.");

                alert.showAndWait();
            } catch(Exception e){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("An error has ocurred.");

                alert.showAndWait();
            }
           
        }
    }
    
  /**
   * This will try to open the sign up window.
   * @param event The clicking event
   */
    @FXML
    private void handleSignUpButtonAction(ActionEvent event) {
        try {
            Parent root = null;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("signUp.fxml"));
            root = (Parent) loader.load();
            FXMLDocumentControllerSignUp viewController = loader.getController();
            Stage secondStage = new Stage();
            secondStage.initModality(Modality.APPLICATION_MODAL);
            viewController.setStage(secondStage);
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
     * Limits the login(username) textfield.
     *
     * @param tf The textfield you want to limit.
     * @param maxLength The maximum length the textfield is going to be.
     */
    public static void addTextLimiter(final TextField tf, final int maxLength) {
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov,
                    final String oldValue, final String newValue) {
                if (tf.getText().length() > maxLength) {
                    String s = tf.getText().substring(0, maxLength);
                    tf.setText(s);
                }
            }
        });
    }
}

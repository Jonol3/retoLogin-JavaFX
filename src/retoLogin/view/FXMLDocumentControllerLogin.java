/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retoLogin.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import retoLogin.User;
import retoLogin.control.Client;
import retoLogin.control.ClientFactory;
import retoLogin.exceptions.*;

/**
 * FXML Controller class
 *
 * @author Daira Eguzkiza
 */
public class FXMLDocumentControllerLogin implements Initializable {

    @FXML
    private Button btnLogin;
    @FXML
    private Button btnSignUp;
    @FXML
    private TextField txtFieldLogin;
    @FXML
    private PasswordField txtFieldPassword;

    User user = new User();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleLoginButtonAction(txtFieldLogin.getText(),
                        txtFieldPassword.getText());
            }
        });

        btnSignUp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleSignUpButtonAction(event);
            }
        });

        addTextLimiter(txtFieldLogin, 30);
        addTextLimiter(txtFieldPassword, 50);
    }

    /**
     * This will try to log in the user. Controlls wether the data entered is
     * valid or not.
     *
     * @param login The username.
     * @param passwd The password for that user.
     * @return
     */
    public int handleLoginButtonAction(String login, String passwd) {
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(login);
        boolean specialChars = m.find();
        //THE LIMITER SHOULD DO ITS JOB, BUT STILL I AM CHECKING THE LENGTH 
        //JUST IN CASE...
        if (login.length() > 30 || specialChars) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Invalid username.");
            alert.setHeaderText(null);
            alert.setContentText("You must enter a valid username.");

            alert.showAndWait();
            return 1;
        } else if (login.length() < 1 || passwd.length() < 1) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Empty username/password.");
            alert.setHeaderText(null);
            alert.setContentText("You must enter a username and a password.");

            alert.showAndWait();
            return 2;
        } else {
            try {
                user.setLogin(login);
                user.setPassword(passwd);

                Client client = ClientFactory.getClient();
                user = client.loginUser(user);

                
                Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass()
                                .getResource("view/signOut.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLDocumentControllerLogin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    FXMLDocumentControllerSignOut controller = new FXMLDocumentControllerSignOut();
                    //controller.setUser(user);
                    //controller.initStage(root);
            
                //TRY TO CONNECT AND ALL THAT MOVIDA
            } catch (LoginException e) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Unexpected error");

                alert.showAndWait();
            } catch (BadLoginException e) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Busy server. Please wait.");

                alert.showAndWait();

            } catch (NoThreadAvailableException e) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Invalid User.");
                alert.setHeaderText(null);
                alert.setContentText("The user you have entered is not correct.");

                alert.showAndWait();
            } catch (BadPasswordException e) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Empty username/password.");
                alert.setHeaderText(null);
                alert.setContentText("The password you have entered is not correct.");

                alert.showAndWait();
            }
           
        }
        return 3;
    }
    
  /**
   * This will try to open the sign up window.
   * @param event
   */
    private void handleSignUpButtonAction(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader()
                    .getResource("view/FXMLDocumentSignUpController"));
            Stage stage = new Stage();
            stage.setTitle("Sign Up");
            stage.setScene(new Scene(root, 450, 450));
            stage.show();
            // Hides this current window (if this is what you want)
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.getMessage();
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

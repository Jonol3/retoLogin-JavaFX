/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retoLogin.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import retoLogin.User;

/**
 * FXML Controller class
 *
 * @author Daira
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
    }
    
    /**
     * this will try to log in the user.
     * Controlls wether the data entered is 
     * valid or not.
     * @param login
     * @param passwd
     */
    
    public int handleLoginButtonAction(String login, String passwd){
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(login);
        boolean specialChars = m.find();
        //THE LIMITER SHOULD DO ITS JOB, BUT STILL I AM CHECKING THE LENGTH 
        //JUST IN CASE...
        if(login.length()>30 || specialChars){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Invalid username.");
            alert.setHeaderText(null);
            alert.setContentText("You must enter a valid username.");
            
            alert.showAndWait();
            return 1;
        }else if(login.length()<1 || passwd.length()<1){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Empty username/password.");
            alert.setHeaderText(null);
            alert.setContentText("You must enter a username and a password.");
            
            alert.showAndWait();
            return 2;
        }else{
            user.setLogin(login);
            user.setPassword(passwd);
                        //TRY TO CONNECT AND ALL THAT MOVIDA
        return 3;
        }
    }
    
    /**
     * Opens the sign up window.
     * @param event 
     */
    private void handleSignUpButtonAction(ActionEvent event){
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/FXMLDocumentSignUpController"));
            Stage stage = new Stage();
            stage.setTitle("Sign Up");
            stage.setScene(new Scene(root, 450, 450));
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.getMessage();
        }
    }
/**
 * Limits the login(username) textfield.
 * @param tf
 * @param maxLength 
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retoLogin.view;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Daira
 */
public class FXMLDocumentControllerLogin implements Initializable {
    @FXML
    private Label lblLogin;
    @FXML
    private Label lblPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnSignUp;
    @FXML
    private TextField txtFieldLogin;
    @FXML
    private TextField txtFieldPassword;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnLogin.setOnAction(this::handleLoginButtonAction);
        btnSignUp.setOnAction(this::handleSignUpButtonAction);
        
        addTextLimiter(txtFieldLogin, 30);
    }
    
    /**
     * When the button is clicked on, this method will try to
     * sign up the user. Controlls wether the data entered is 
     * valid or not.
     * @param e 
     */
    private void handleLoginButtonAction(ActionEvent e){
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher("I am a string");
        boolean specialChars = m.find();
        //THE LIMITER SHOULD DO ITS JOB, BUT STILL I AM CHECKING THE LENGTH 
        //JUST IN CASE...
        if(txtFieldLogin.getText().length()>30 || specialChars){
           
        }else if(txtFieldLogin.getText().length()<1 || txtFieldPassword
                .getText().length()<1){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Empty username.");
            alert.setHeaderText(null);
            alert.setContentText("You must enter a username.");
            
            alert.showAndWait();
        }else{
                        //TRY TO CONNECT AND ALL THAT MOVIDA

        }
    }
    
    /**
     * Opens the sign up window.
     * @param e 
     */
    private void handleSignUpButtonAction(ActionEvent e){
       
    }
/**
 * Limits the sent textfield.
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retoLogin.view;

import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;



/**
 * FXML Controller class
 *
 * @author Unai Pérez Sánchez
 */
public class FXMLDocumentControllerSignUp{
    private Logger LOGGER = Logger.getLogger("retoLogin.view.FXMLDocumentControllerSignUp");
    @FXML
    private TextField tfFullName;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfLogin;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private PasswordField pfConfirm;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnUndo;
    @FXML
    private Button btnRedo;
    @FXML
    private Button btnRegister;
    /**
     * Initializes the controller class.
     */
    public void initialize() {
        //Register button will be disabled
        btnRegister.setDisable(true);
        
        btnRegister.setOnAction(this::handleBtnRegister);
        //Redo button will be disabled
        btnRedo.setDisable(true);
        //Full Name TextField will have the focus
        tfFullName.requestFocus();
    }    
    
    public void handleBtnCancel(ActionEvent e){
        
    }
    
    public void handleBtnUndo(ActionEvent e){
        
    }
    
    public void handleBtnRedo(ActionEvent e){
        
    }
    
    public void handleBtnRegister(ActionEvent e){
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retoLogin.view;

import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import retoLogin.User;



/**
 * FXML Controller class
 *
 * @author Unai Pérez Sánchez
 */
public class FXMLDocumentControllerSignUp{
    private Logger LOGGER = Logger.getLogger("retoLogin.view.FXMLDocumentControllerSignUp");
    
    private Stage stage;
    
    private User user = new User();
    
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
    public void initStage(Parent root) {
        LOGGER.info("Initializing Sign Up stage");
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        
        stage.setTitle("Sign Up");
        stage.setResizable(false);
        stage.setOnShowing(this::handleWindowShowing);
        
        btnRegister.setOnAction(this::handleBtnRegister);
        btnRedo.setOnAction(this::handleBtnRedo);
        btnUndo.setOnAction(this::handleBtnUndo);
        btnCancel.setOnAction(this::handleBtnCancel);
        tfEmail.textProperty().addListener(this::textChanged);
        tfFullName.textProperty().addListener(this::textChanged);
        tfLogin.textProperty().addListener(this::textChanged);
        pfPassword.textProperty().addListener(this::textChanged);
        pfConfirm.textProperty().addListener(this::textChanged);
        
        
        stage.show();
    }    
    
    public void textChanged(ObservableValue observable, String oldValue, String newValue){
        if(tfLogin.getText().trim().length()>50){
            LOGGER.warning("The Login field is too long");
        }else if(tfFullName.getText().trim().length()>85){
            LOGGER.warning("The Full Name field is too long");
        }else if(tfEmail.getText().trim().length()>80){
            LOGGER.warning("The Email field is too long");
        }else if(pfPassword.getText().trim().length()>200 ||
                pfConfirm.getText().trim().length()>200){
            LOGGER.warning("The Password field is too long");
        }else if(tfEmail.getText().trim().isEmpty()||
                tfFullName.getText().trim().isEmpty()||
                tfLogin.getText().trim().isEmpty()||
                pfConfirm.getText().trim().isEmpty()||
                pfPassword.getText().trim().isEmpty()){
            LOGGER.info("Some field is empty, the register button is going to be dissabled");
            btnRegister.setDisable(true);
        }else{
            btnRegister.setDisable(false);
        }
    }
    
    public void handleWindowShowing(WindowEvent e){
        //Register button will be disabled
        btnRegister.setDisable(true);
        //Redo button will be disabled
        btnRedo.setDisable(true);
        //Full Name TextField will have the focus
        tfFullName.requestFocus();
        
        btnCancel.setMnemonicParsing(true);
        btnCancel.setText("_Cancel");
        
        btnUndo.setMnemonicParsing(true);
        btnUndo.setText("_Undo");
        
        btnRedo.setMnemonicParsing(true);
        btnRedo.setText("_Redo");
        
        btnRegister.setMnemonicParsing(true);
        btnRegister.setText("_Sign Up");
    }
    
    public void handleBtnCancel(ActionEvent e){
        //TODO
    }
    
    public void handleBtnUndo(ActionEvent e){
        user.setFullName(tfFullName.getText());
        user.setEmail(tfEmail.getText());
        user.setLogin(tfLogin.getText());
        tfFullName.setText("");
        tfEmail.setText("");
        tfLogin.setText("");
        pfPassword.setText("");
        pfConfirm.setText("");
        btnRedo.setDisable(false);
    }
    
    public void handleBtnRedo(ActionEvent e){
        tfFullName.setText(user.getFullName());
        tfEmail.setText(user.getEmail());
        tfLogin.setText(user.getLogin());
        btnRedo.setDisable(true);
    }
    
    public void handleBtnRegister(ActionEvent e){
        Alert alert;
        //Alert if the password isn't equal
        if(!pfPassword.getText().equals(pfConfirm.getText())){
            LOGGER.warning("The password and the confirm password fields doesm't have the same information");
            alert = new Alert(Alert.AlertType.ERROR, "The passwords arent equal");
            alert.show();
        }else{
            //TODO
        }
    }
    
    public void setStage(Stage stage){
        this.stage=stage;
    }
    
    public Stage getStage(){
        return stage;
    }
}

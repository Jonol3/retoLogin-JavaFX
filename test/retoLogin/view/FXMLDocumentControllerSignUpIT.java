/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retoLogin.view;

import javafx.stage.Stage;
import org.junit.Test;
import javafx.scene.control.TextField;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;

import retoLogin.Application;

/**
 *
 * @author Unai Pérez Sánchez
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FXMLDocumentControllerSignUpIT extends ApplicationTest{
    
    private static final String OVERSIZED_TEXT_LOGIN="XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
            + "XXXXXXXXXXXXXXXXXXXXX";
    private static final String OVERSIZED_TEXT_EMAIL="XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
            + "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    private static final String OVERSIZED_TEXT_FULLNAME="XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
            + "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    private static final String OVERSIZED_TEXT_PASSWORD="XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
            + "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
            + "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
            + "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    /**
     * Starts the application to be tested
     * @param stage Object of type Stage
     * @throws Exception If there is any error a exception will be thrown
     */
    @Override
    public void start(Stage stage) throws Exception {
        new Application().start(stage);
    }
    /**
     * This method allows to see sign up view by interacting with login
     */
    @Test
    public void testA_initialInteraction(){
        clickOn("#btnSignUp");
        verifyThat("#signUpPane",isVisible());
    }
    /**
     * This method tests the initial state of the Sign Up view
     */
    @Test
    public void testB_initialState(){
        verifyThat("#tfFullName", hasText(""));
        verifyThat("#tfEmail", hasText(""));
        verifyThat("#tfLogin", hasText(""));
        verifyThat("#pfPassword", hasText(""));
        verifyThat("#pfConfirm", hasText(""));
        verifyThat("#btnCancel", isEnabled());
        verifyThat("#btnUndo", isEnabled());
        verifyThat("#btnRedo", isDisabled());
        verifyThat("#btnRegister", isDisabled());
        verifyThat("#tfFullName", (TextField t)->t.isFocused());
    }
    /**
     * This method checks if all the fields have text the register button is enabled
     * if some field is empty the button will be disabled
     */
    @Test
    public void testC_checkEnableDisableRegister(){
        clickOn("#tfFullName");
        write("aaaa");
        verifyThat("#btnRegister", isDisabled());
        clickOn("#tfEmail");
        write("aaaa");
        verifyThat("#btnRegister", isDisabled());
        clickOn("#tfLogin");
        write("aaaa");
        verifyThat("#btnRegister", isDisabled());
        clickOn("#pfPassword");
        write("aaaa");
        verifyThat("#btnRegister", isDisabled());
        clickOn("#pfConfirm");
        write("aaaa");
        verifyThat("#btnRegister", isEnabled());
        clickOn("#tfFullName");
        eraseText(5);
        clickOn("#tfEmail");
        eraseText(5);
        clickOn("#tfLogin");
        eraseText(5);
        clickOn("#pfPassword");
        eraseText(5);
        clickOn("#pfConfirm");
        eraseText(5);
    }
    /**
     * This method checks that the function of the undo and the redo is working
     * properly
     */
    @Test
    public void testD_checkUndoRedoWorks(){
        clickOn("#tfFullName");
        write("aaaa");
        clickOn("#tfEmail");
        write("aaaa");
        clickOn("#tfLogin");
        write("aaaa");
        verifyThat("#btnRedo", isDisabled());
        clickOn("#btnUndo");
        verifyThat("#tfFullName", hasText(""));
        verifyThat("#tfEmail", hasText(""));
        verifyThat("#tfLogin", hasText(""));
        verifyThat("#btnRedo", isEnabled());
        clickOn("#btnRedo");
        verifyThat("#tfFullName", hasText("aaaa"));
        verifyThat("#tfEmail", hasText("aaaa"));
        verifyThat("#tfLogin", hasText("aaaa"));
        verifyThat("#btnRedo", isDisabled());
        clickOn("#tfFullName");
        eraseText(5);
        clickOn("#tfEmail");
        eraseText(5);
        clickOn("#tfLogin");
        eraseText(5);
    }
    /**
     * This method checks if the size of the text in the fields is the correct one
     * if it is not the register button will be dissabled and an error message will
     * be displayed
     */
    @Test
    public void testE_sizeIsCorrect(){
        clickOn("#tfFullName");
        write("aaaa");
        clickOn("#tfEmail");
        write("aaaa");
        clickOn("#tfLogin");
        write("aaaa");
        clickOn("#pfPassword");
        write("aaaa");
        clickOn("#pfConfirm");
        write("aaaa");
        verifyThat("#btnRegister", isEnabled());
        clickOn("#tfFullName");
        eraseText(5);
        write(OVERSIZED_TEXT_FULLNAME);
        verifyThat("The Full Name field is too long", isVisible());
        clickOn("Aceptar");
        verifyThat("#btnRegister", isDisabled());
        eraseText(OVERSIZED_TEXT_FULLNAME.length());
        write("aaaa");
        verifyThat("#btnRegister", isEnabled());
        clickOn("#tfEmail");
        eraseText(5);
        write(OVERSIZED_TEXT_EMAIL);
        verifyThat("The Email field is too long", isVisible());
        clickOn("Aceptar");
        verifyThat("#btnRegister", isDisabled());
        eraseText(OVERSIZED_TEXT_EMAIL.length());
        write("aaaa");
        clickOn("#tfLogin");
        eraseText(5);
        write(OVERSIZED_TEXT_LOGIN);
        verifyThat("The Login field is too long", isVisible());
        clickOn("Aceptar");
        verifyThat("#btnRegister", isDisabled());
        eraseText(OVERSIZED_TEXT_LOGIN.length());
        write("aaaa");
        clickOn("#pfPassword");
        eraseText(5);
        write(OVERSIZED_TEXT_PASSWORD);
        verifyThat("The Password field is too long", isVisible());
        clickOn("Aceptar");
        verifyThat("#btnRegister", isDisabled());
        eraseText(OVERSIZED_TEXT_PASSWORD.length());
        write("aaaa");
        clickOn("#pfConfirm");
        eraseText(5);
        write(OVERSIZED_TEXT_PASSWORD);
        verifyThat("The Password field is too long", isVisible());
        clickOn("Aceptar");
        verifyThat("#btnRegister", isDisabled());
        eraseText(OVERSIZED_TEXT_PASSWORD.length());
        write("aaaa");
    }
    /**
     * This method checks if the login and the email field is correct
     * also checks if the password and it's confirmation are equal
     */
    @Test
    public void testF_correctFormat(){
        clickOn("#tfFullName");
        write("aaaa");
        clickOn("#tfEmail");
        write("aaaa");
        clickOn("#tfLogin");
        write("aaaa");
        clickOn("#pfPassword");
        write("aaaa");
        clickOn("#pfConfirm");
        write("aaaa");
        clickOn("#btnRegister");
        verifyThat("The email is not valid, please enter a new one.", isVisible());
        clickOn("Aceptar");
        clickOn("#tfEmail");
        eraseText(5);
        write("a@a.com");
        clickOn("#tfLogin");
        eraseText(5);
        write("aaaa#.");
        clickOn("#btnRegister");
        verifyThat("The login is not valid, please enter a new one", isVisible());
        clickOn("Aceptar");
        clickOn("#tfLogin");
        eraseText(7);
        write("aaaa");
        clickOn("#pfPassword");
        eraseText(1);
        clickOn("#btnRegister");
        verifyThat("The passwords are not equal.", isVisible());
        clickOn("Aceptar");
        clickOn("#pfPassword");
        write("a");
        clickOn("#pfConfirm");
        eraseText(1);
        clickOn("#btnRegister");
        verifyThat("The passwords are not equal.", isVisible());
        clickOn("Aceptar");
        clickOn("#pfConfirm");
        write("a");
    }
}

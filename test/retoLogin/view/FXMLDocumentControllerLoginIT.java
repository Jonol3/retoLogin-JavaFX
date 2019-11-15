/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retoLogin.view;

import javafx.stage.Stage;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import org.testfx.robot.Motion;
import retoLogin.Application;

/**
 * The integration test for the FXMLDocumentControllerLogin.
 * @author Daira Eguzkiza
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FXMLDocumentControllerLoginIT extends ApplicationTest{
    private static final String INVALIDUSERNAME="user$name·?%";
    private static final String LONGUSERNAME="dkufbiuapbdfiabfvibfvivfirfbie"
            + "aaasdiufbaoisdbiaoslbdfaisdfaa"
            + "duhfaildfhgioadgbiladfbviadbvi"
            + "dkhjbfliadfbipadbgadfguabgalid"
            + "dkufbiuapbdfiabfvibfvivfirfbie"
            + "gfsbdisdbgigbiadfgbadfgbgdfiuf"
            + "dljfngsdikfbnskdfbiñsfbñsidfif"
            + "dfgkjbdfibdfiabdfidbfsdkfbsdkf"
            + "dfkjnsdifbsklfdbjnslkfbjksfdjf";
    private static final String PASSWORD="password";
    private static final String USEREXISTS="jon";
    private static final String NONEXISTENTUSER="inventeduser123";
    private static final String PASSWORDMATCH="abcd*1234";
    
    
 
    @Override public void start(Stage stage) throws Exception {
      new Application().start(stage);
    }
    
    /** 
     * Tests if everything goes right when you enter valid parameters.
     */
    @Test
    public void testZLoginButtonIfOk() {
        clickOn("#txtFieldLogin");
        write(USEREXISTS);
        clickOn("#txtFieldPassword");
        write(PASSWORDMATCH);
        clickOn("#btnLogin");
        verifyThat("Welcome, Jon Calvo Gaminde", isVisible());
    }
    
    /**
     * Tests if when you try to log in without password, the application shows
     * an alert dialog saying you must enter a password/user.
     */
    @Test
    public void testALoginButtonWithoutPasswd() {
        clickOn("#txtFieldLogin");
        write(USEREXISTS);
        clickOn("#txtFieldPassword");
        write("");
        clickOn("#btnLogin");
        verifyThat("You must enter a username and a password.", isVisible());
        clickOn("Aceptar");
    }
    
    /**
     * Tests if when you try to log in with a non existent user the 
     * application shows an alert dialog saying that the user doesn't exist.
     */
    @Test
    public void testBLoginButtonNonExistingUser() {
        clickOn("#txtFieldLogin");
        write(NONEXISTENTUSER);
        clickOn("#txtFieldPassword");
        write(PASSWORD);
        clickOn("#btnLogin");
        verifyThat("That user does not exist.", isVisible());
        clickOn("Aceptar");
    }
    
    /**
     * Tests if when you try to log in without entering a username the 
     * application shows an alert dialog saying that you must enter a 
     * username/password.
     */
    @Test
    public void testCLoginButtonWithoutUser() {
        clickOn("#txtFieldLogin");
        write("");
        clickOn("#txtFieldPassword");
        write(PASSWORD);
        clickOn("#btnLogin");
        verifyThat("You must enter a username and a password.", isVisible());
        clickOn("Aceptar");
    }
    
    /**
     * Tests if when you try to log in with an invalid username the 
     * application shows an alert dialog saying you must enter a valid
     * username.
     */
    @Test
    public void testDLoginButtonWithInvalidUsername() {
        clickOn("#txtFieldLogin");
        write(INVALIDUSERNAME);
        clickOn("#txtFieldPassword");
        write(PASSWORD);
        clickOn("#btnLogin");
        verifyThat("You must enter a valid username.", isVisible());
        clickOn("Aceptar");
    }
    
    /**
     * Tests if when you try to log in with a wrong password the application
     * shows an alert dialog saying that the password is not correct.
     */
    @Test
    public void testELoginButtonWithWrongPassw() {
        
        clickOn("#txtFieldLogin");
        write(USEREXISTS);
        clickOn("#txtFieldPassword");
        write(PASSWORD);
        clickOn("#btnLogin");
        verifyThat("The password you have entered is not correct.", isVisible());
        clickOn("Aceptar");
    }
    /*
    @Test
    public void testEEAbout() {
        clickOn("#menuHelp");
        clickOn("#about");
        
        verifyThat("Version 1.0", isVisible());
        clickOn("Aceptar");
    }
    
     @Test
    public void testEAHowItWorks() {
        clickOn("#menuHelp");
        clickOn("#howItWorks");
        
        verifyThat("Login/LogOut: Help", isVisible());
        clickOn("Aceptar");
        moveTo("#pnHelp");
        moveBy(375, -300, Motion.DIRECT);
        clickOn();
    }
    */
    
    /**
     * Tests if when you try to enter a really long username it reaches a point
     * where you can't write more, but if for some reason this fails, it must
     * show an alert dialog saying that you must enter a valid username).
     */
   @Test
    public void testFLoginButtonWithMastodonteUsername() {
        clickOn("#txtFieldLogin");
        write(LONGUSERNAME);
        clickOn("#txtFieldPassword");
        write(PASSWORD);
        clickOn("#btnLogin");
        if("#txtFieldLogin".length()>50){
           verifyThat("You must enter a valid username.", isVisible());
        }
        clickOn("Aceptar");
    }
    
    @Test
    public void testFLoginButtonWithMastodontePassword() {
        clickOn("#txtFieldLogin");
        write(NONEXISTENTUSER);
        clickOn("#txtFieldPassword");
        write(LONGUSERNAME);
        clickOn("#btnLogin");
        if("#txtFieldPassword".length()>200){
           verifyThat("The pass", isVisible());
        }
        clickOn("Aceptar");
    }
    
    /**
     * Tests if when you click on the sign up button, another window is opened
     * showing the sign up options.
     */
   @Test
    public void testGSignUpBtn() {
        clickOn("#btnSignUp");
        verifyThat("Sign Up", isVisible());
        clickOn("#btnCancel");
    }
}

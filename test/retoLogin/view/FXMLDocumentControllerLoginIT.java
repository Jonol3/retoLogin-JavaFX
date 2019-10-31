/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retoLogin.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import retoLogin.Application;

/**
 *
 * @author Daira Eguzkiza
 */
public class FXMLDocumentControllerLoginIT extends ApplicationTest{
    private static final String INVALIDUSERNAME="user$name·?%";
    private static final String VALIDUSERNAME="username1234";
    private static final String LONGUSERNAME="isauhdfiuasfdbiadfbdfiugadifdf"
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
    //VERIFY THAT XpANE IS VISIBLE()
    //Assert.assertNotNull();
    
    
    
    @Test
    public void testLoginButtonIfOk() {
        clickOn("#txtFieldLogin");
        write(USEREXISTS);
        clickOn("#txtFieldPassword");
        write(PASSWORDMATCH);
        clickOn("#btnLogin");
        verifyThat("Welcome, Jon Calvo Gaminde", isVisible());
    }
    
    @Test
    public void testLoginButtonWithoutPasswd() {
        clickOn("#txtFieldLogin");
        write(USEREXISTS);
        clickOn("#txtFieldPassword");
        write("");
        clickOn("#btnLogin");
        verifyThat("You must enter a username and a password.", isVisible());
    }
    
    @Test
    public void testLoginButtonNonExistingUser() {
        clickOn("#txtFieldLogin");
        write(NONEXISTENTUSER);
        clickOn("#txtFieldPassword");
        write(PASSWORD);
        clickOn("#btnLogin");
        verifyThat("That user does not exist.", isVisible());
    }
    
    @Test
    public void testLoginButtonWithoutUser() {
        clickOn("#txtFieldLogin");
        write("");
        clickOn("#txtFieldPassword");
        write(PASSWORD);
        clickOn("#btnLogin");
        verifyThat("You must enter a username and a password.", isVisible());
    }
    
    @Test
    public void testLoginButtonWithWrongPassw() {
        clickOn("#txtFieldLogin");
        write(USEREXISTS);
        clickOn("#txtFieldPassword");
        write(PASSWORD);
        clickOn("#btnLogin");
        verifyThat("The password you have entered is not correct.", isVisible());
    }
    
   @Test
    public void testLoginButtonWithMastodonteUsername() {
        clickOn("#txtFieldLogin");
        write(LONGUSERNAME);
        clickOn("#txtFieldPassword");
        write(PASSWORD);
        clickOn("#btnLogin");
        verifyThat("That user does not exist.", isVisible());
    }
    
   @Test
    public void testSignUpBtn() {
        clickOn("#btnSignUp");
        verifyThat("Sign Up", isVisible());
        
    }
    
    
}

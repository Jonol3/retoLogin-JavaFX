package retoLogin.view;

import com.sun.glass.ui.Window;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import org.testfx.robot.Motion;
import retoLogin.Application;

/**
 * The Integration Test for the FXMLDocumentControllerSignOut
 * @author Jon Calvo Gaminde
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FXMLDocumentControllerSignOutIT extends ApplicationTest{
    private static boolean logged = false;
    @Override
    public void start(Stage stage) throws Exception {
      new Application().start(stage);
      
    }
    
    /**
     * Inserts a valid user in the login once, before any other test, to open
     * the LogOut window.
     */
    @Before
    public void openLogOut() {
        if (!logged) {
            clickOn("#txtFieldLogin");
            write("jon");
            clickOn("#txtFieldPassword");
            write("abcd*1234");
            clickOn("#btnLogin");
            logged = true;
        }
    }
    
    /**
     * Tests if the welcome message shows.
     */
    @Test
    public void testA_Welcome() {
        verifyThat("Welcome, Jon Calvo Gaminde", isVisible());
        
    }
    
    /**
     * Tests if the help page shows when clicking the option in the menu.
     */
    @Test
    public void testB_WorksButton() {
        clickOn("#mntHelp");
        clickOn("#mnddWorks");
        verifyThat("#pnHelp", isVisible());
        moveTo("#pnHelp");
        moveBy(375, -300, Motion.DIRECT);
        clickOn();
   }
    
    /**
     * Tests if the about message shows when clicking the option in the menu.
     */
    @Test
    public void testC_AboutButton() {
        clickOn("#mntHelp");
        clickOn("#mnddAbout");
        verifyThat("Version 1.0", isVisible());
        clickOn("Aceptar");
    }
    
    
    /**
     * Tests if the application returns to the login window when clicking 
     * the Log out button.
     */
    @Test
    public void testD_LogOutButton() {
        clickOn("#btLogOut");
        verifyThat("#pnLogin", isVisible());
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retoLogin.view;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 2dam
 */
public class FXMLDocumentControllerLoginIT {
    private static final String INVALIDUSERNAME="user$name·?%";
    private static final String VALIDUSERNAME="username1234";
    private static final String PASSWORD="password";
    
    
    public FXMLDocumentControllerLoginIT() {
    }

    @Test
    public void testLoginButton() {
        FXMLDocumentControllerLogin clase = new FXMLDocumentControllerLogin();
         assertEquals("", clase
                 .handleLoginButtonAction(INVALIDUSERNAME,PASSWORD), 1);   
         assertEquals("", clase
                 .handleLoginButtonAction(VALIDUSERNAME,""), 2);  
         assertEquals("", clase
                 .handleLoginButtonAction("",PASSWORD), 2); 
         assertEquals("", clase
                 .handleLoginButtonAction("",""), 2);
         assertEquals("", clase
                 .handleLoginButtonAction(VALIDUSERNAME,PASSWORD), 3);   
    }
    
}

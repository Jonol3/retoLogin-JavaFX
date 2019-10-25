/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retoLogin.control;

import retoLogin.User;
import retoLogin.exceptions.*;
/**
 *
 * @author Daira Eguzkiza
 */
public interface Client {
    
    
    public User loginUser(User user) throws LoginException, 
            BadLoginException, BadPasswordException, NoThreadAvailableException;
    
    public User registerUser(User user) throws RegisterException, 
            AlreadyExistsException, NoThreadAvailableException;
    
    /*public void prueba();*/

    
    
}

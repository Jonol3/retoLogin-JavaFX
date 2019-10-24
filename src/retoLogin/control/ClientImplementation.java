/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retoLogin.control;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
import retoLogin.Message;
import retoLogin.User;
import retoLogin.exceptions.*;
/**
 *
 * @author Daira
 */
public class ClientImplementation implements Client{
    private final int PUERTO = 5001;
    private final String IP = "127.0.0.1";
        
    public static void main(String[] args) {
         ClientImplementation c = new ClientImplementation();
         c.prueba();
    }
    
    
    public void prueba() {
      User u= new User();
      u.setLogin("abc");
      u.setPassword("dfghdgfh");
      u.setEmail("abd@gmail.com");
      u.setFullName("Nombre");
       
       Message message = new Message(); 
       message.setUser(u);
       message.setType(1);
       Socket cliente = null;
       ObjectInputStream entrada = null;
       ObjectOutputStream salida = null;
       
       try {

		cliente = new Socket(IP, PUERTO);
		System.out.println("Conexión realizada con servidor");

		salida = new ObjectOutputStream(cliente.getOutputStream());
		entrada = new ObjectInputStream(cliente.getInputStream()); 	
	
		salida.writeObject(message);
                
                //HACERLE ESPERAR Y TAL
                
                Message m = (Message) entrada.readObject();
                int result = m.getType();
                
           
	} catch (IOException e) {
		System.out.println("Error: " + e.getMessage());
	} catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
		try {
			if (cliente != null)
				cliente.close();
			if (entrada != null)
				entrada.close();

			if (salida != null)
				salida.close();
		} catch (IOException e) {
			e.getStackTrace();
		}
	}
       
    }
    
    
    @Override
    public User loginUser(User user) throws LoginException, 
       BadLoginException, BadPasswordException, NoThreadAvailableException{
       Message message = new Message(); 
       Socket cliente = null;
       ObjectInputStream entrada = null;
       ObjectOutputStream salida = null;
                try {

		cliente = new Socket(IP, PUERTO);
		System.out.println("Conexión realizada con servidor");

		salida = new ObjectOutputStream(cliente.getOutputStream());
		entrada = new ObjectInputStream(cliente.getInputStream()); 	
		
                message.setUser(user);
                message.setType(1);
		salida.writeObject(message);
                
                //HACERLE ESPERAR Y TAL
                
                Message m = (Message) entrada.readObject();
                int result = m.getType();
                
                switch (result) {
                    case 0:
                        user= m.getUser();
                        return user;
                    case 1:
                        throw new LoginException("Error trying to log in.");
                    case 2:
                        throw new BadLoginException("Bad login.");
                    case 3:
                        throw new
                           BadPasswordException("The password is not correct.");
                    default:
                        throw new
                           NoThreadAvailableException("Server Busy.");
                }
	} catch (IOException | ClassNotFoundException | 
                BadLoginException | BadPasswordException | 
                LoginException | NoThreadAvailableException e) {
		System.out.println("Error: " + e.getMessage());
	} finally {
		try {
			if (cliente != null)
				cliente.close();
			if (entrada != null)
				entrada.close();

			if (salida != null)
				salida.close();
		} catch (IOException e) {
			e.getStackTrace();
		}
	}
       return user;
    }

    @Override
    public Message registerUser(User user) throws RegisterException, AlreadyExistsException {
       Message message = new Message(); 
       Socket cliente = null;
       ObjectInputStream entrada = null;
       ObjectOutputStream salida = null;
                try {

		cliente = new Socket(IP, PUERTO);
		System.out.println("Conexión realizada con servidor");

		salida = new ObjectOutputStream(cliente.getOutputStream());
		entrada = new ObjectInputStream(cliente.getInputStream()); 
			
		
		salida.writeObject(user);

	} catch (IOException e) {

		System.out.println("Error: " + e.getMessage());
	} catch (Exception e) {

		System.out.println("Error: " + e.getMessage());
	} finally {

		try {

			if (cliente != null)
				cliente.close();
			if (entrada != null)
				entrada.close();

			if (salida != null)
				salida.close();
		} catch (IOException e) {
			e.getStackTrace();
		}
	}
       return message;
    }
}

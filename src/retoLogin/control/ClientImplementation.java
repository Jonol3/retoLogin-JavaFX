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
import retoLogin.Message;
import retoLogin.User;
import retoLogin.exceptions.*;
/**
 *
 * @author Daira Eguzkiza
 */
public class ClientImplementation implements Client{
    private final int PUERTO = 5001;
    private final String IP = "192.168.21.82";
    private static final Logger LOGGER =  Logger
            .getLogger("retoLogin.control.ClientImplementation");
    
    public static void main(String[] args) {
        ClientImplementation c = new ClientImplementation();
        
         User u = new User();
         u.setLogin("Jonzdfgzdfg");
         u.setPassword("abc*1234");
                    
         User newUser = new User();
         newUser.setEmail("alguien@gmail.com");
         newUser.setFullName("alguien González");
         newUser.setLogin("Alguien");
         newUser.setPassword("abcd*1234");
         newUser.setPrivilege(1);
         newUser.setStatus(1);
         
        //PRUEBA CON LOGIN AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA          
        /*
        try {
            c.loginUser(u);
        } catch (LoginException | BadLoginException | BadPasswordException | 
                NoThreadAvailableException ex) {
            Logger.getLogger(ClientImplementation.class.getName())
                    .log(Level.SEVERE, null, ex);
        }*/
        //FIN PRUEBAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        //PRUEBA CON REGISTRO AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        try {
            c.registerUser(newUser);
        } catch (RegisterException | AlreadyExistsException | 
                NoThreadAvailableException ex) {
            Logger.getLogger(ClientImplementation.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        // FIN PRUEBAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        
    }
   
    /**
     * Method that connects with a server using a socket and returns a message 
     * with the user that's trying to log in (if everything's okay) and the 
     * response type.
     * @param user A username and password received by the login window. 
     * @return The full user that gets from the server (if it has it).
     * @throws LoginException An unknown error occurred while trying to log in
     * the user.
     * @throws BadLoginException The entered user does not exist on the database.
     * @throws BadPasswordException The password doesn't match with the one from
     * the database.
     * @throws NoThreadAvailableException The maximum number of threads available
     * for the clients has been reached.
     */
    @Override
    @SuppressWarnings("LoggerStringConcat")
    public User loginUser(User user) throws LoginException, 
       BadLoginException, BadPasswordException, NoThreadAvailableException{
       Message message = new Message(); 
       Socket cliente = null;
       ObjectInputStream entrada = null;
       ObjectOutputStream salida = null;
                try {
                    cliente = new Socket(IP, PUERTO);
                    LOGGER.info("Conexión realizada con el servidor");

                    salida = new ObjectOutputStream(cliente.getOutputStream());
                    entrada = new ObjectInputStream(cliente.getInputStream());
 
                    LOGGER.info("Mando el user");
                    message.setUser(user);
                    message.setType(1);
                    salida.writeObject(message);
                    LOGGER.info("Enviado");
                    //HACERLE ESPERAR Y TAL
                    Message m = (Message) entrada.readObject();
                    LOGGER.info("Objeto recibido");
                    LOGGER.info("respuesta: " + m.getType());
                     
            switch ( m.getType()) {
                case 0:
                    user = m.getUser();
                    LOGGER.info(user.getFullName() + " "
                            + user.getEmail());
                    return user;
                case 1:
                    throw new LoginException("Error trying to log in.");
                case 2:
                    throw new NoThreadAvailableException("Server is busy.");
                case 3:
                    throw new BadLoginException("Bad login.");
                    
                case 4:
                    throw new BadPasswordException("The password is not "
                            + "correct.");
                    
            }
        } catch (IOException | ClassNotFoundException
                | BadLoginException | BadPasswordException
                | LoginException | NoThreadAvailableException e) {
            LOGGER.severe("Error: " + e.getMessage());
        } finally {
            try {
                if (cliente != null) {
                    cliente.close();
                }
                if (entrada != null) {
                    entrada.close();
                }

                if (salida != null) {
                    salida.close();
                }
            } catch (IOException e) {
                e.getStackTrace();
            }
        }
        return user;
    }

    /**
     * Method that connects with a server using a socket and returns a message
     * with the registered user (if everything's gone right) and the response
     * type.
     * @param user All the data from a user received by the sign up window.
     * @return The user once is signed in (if everything's gone right.)
     * @throws RegisterException There's been an exception while trying to sign 
     * in the user.
     * @throws AlreadyExistsException The username is already in use by another
     * user.
     * @throws NoThreadAvailableException The maximum number of threads available
     * for the clients has been reached.
     */
    @Override
    @SuppressWarnings("LoggerStringConcat")
    public User registerUser(User user) throws RegisterException,
        AlreadyExistsException, NoThreadAvailableException {
        Message message = new Message();
        Socket cliente = null;
        ObjectInputStream entrada = null;
        ObjectOutputStream salida = null;
        try {
            cliente = new Socket(IP, PUERTO);
            LOGGER.info("Conexión realizada con servidor");

            salida = new ObjectOutputStream(cliente.getOutputStream());
            entrada = new ObjectInputStream(cliente.getInputStream());

            message.setUser(user);
            message.setType(2);
            salida.writeObject(message);

            Message m = (Message) entrada.readObject();
            //LOGGER.info("Registrado bien: " + m.getUser().getFullName());
            switch (m.getType()) {
                case 0:
                     LOGGER.info("TODO BIEN");
                    return user;
                case 1:
                    throw new RegisterException("Error trying to log in.");
                case 2:
                    throw new NoThreadAvailableException("Server is busy.");
                case 3:
                    throw new AlreadyExistsException("User already exists.");
            }

        } catch (IOException | ClassNotFoundException | AlreadyExistsException |
                NoThreadAvailableException | RegisterException e) {
            LOGGER.severe("Error: " + e.getMessage());
        } finally {

            try {
                if (cliente != null) {
                    cliente.close();
                }
                if (entrada != null) {
                    entrada.close();
                }

                if (salida != null) {
                    salida.close();
                }
            } catch (IOException e) {
                LOGGER.severe("Error: " + e.getMessage());
            }
        }
        return user;
    }
}

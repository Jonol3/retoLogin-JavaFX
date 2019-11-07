/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retoLogin.control;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import retoLogin.Message;
import retoLogin.User;
import retoLogin.exceptions.*;

/**
 * This is the class that implements the Client methods.
 * @author Daira Eguzkiza, Jon Calvo Gaminde
 */
public class ClientImplementation implements Client {

    private static final Logger LOGGER = Logger
            .getLogger("retoLogin.control.ClientImplementation");
    ResourceBundle properties = ResourceBundle.getBundle("retoLogin.clientConnection");
    private final String IP = properties.getString("serverIp");
    private final int PUERTO = Integer.parseInt(properties.getString("serverPort"));
    
    @Override
    @SuppressWarnings("LoggerStringConcat")
    public User loginUser(User user) throws LoginException,
            BadLoginException, BadPasswordException, NoThreadAvailableException {
        Message message = new Message();
        Socket cliente = null;
        ObjectInputStream entrada = null;
        ObjectOutputStream salida = null;
        try {
            cliente = new Socket();
            cliente.connect(new InetSocketAddress(IP,PUERTO), 2000);
            LOGGER.info("Connected with the server.");

            salida = new ObjectOutputStream(cliente.getOutputStream());
            entrada = new ObjectInputStream(cliente.getInputStream());

            LOGGER.info("Sending message...");
            message.setUser(user);
            message.setType(1);
            salida.writeObject(message);
            LOGGER.info("Message sent.");
            Message m = (Message) entrada.readObject();
            LOGGER.info("Answer received.");

            switch (m.getType()) {
                case 0:
                    user = m.getUser();
                    LOGGER.info("Login successful.");
                    return user;
                case 1:
                    throw new LoginException("Error trying to log in.");
                case 2:
                    throw new NoThreadAvailableException("Server is busy.");
                case 3:
                    throw new BadLoginException("The login is not "
                            + "correct.");
                case 4:
                    throw new BadPasswordException("The password is not "
                            + "correct.");
            }
        } catch (IOException | ClassNotFoundException e) {
            //SocketTimeoutException and SocketException are caught by the IOException
            LOGGER.severe("Error: " + e.getMessage());
            throw new LoginException("Error trying to log in.");
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

    @Override
    @SuppressWarnings("LoggerStringConcat")
    public User registerUser(User user) throws RegisterException,
            AlreadyExistsException, NoThreadAvailableException {
        Message message = new Message();
        Socket cliente = null;
        ObjectInputStream entrada = null;
        ObjectOutputStream salida = null;
        try {
            cliente = new Socket();
            cliente.connect(new InetSocketAddress(IP,PUERTO), 2000);
            LOGGER.info("Connected with the server.");

            salida = new ObjectOutputStream(cliente.getOutputStream());
            entrada = new ObjectInputStream(cliente.getInputStream());

            LOGGER.info("Sending message...");
            message.setUser(user);
            message.setType(2);
            salida.writeObject(message);
            LOGGER.info("Message sent.");

            Message m = (Message) entrada.readObject();
            LOGGER.info("Answer received.");
            switch (m.getType()) {
                case 0:
                    LOGGER.info("Register successful.");
                    return user;
                case 1:
                    throw new RegisterException("Error trying to log in.");
                case 2:
                    throw new NoThreadAvailableException("Server is busy.");
                case 3:
                    throw new AlreadyExistsException("User already exists.");
            }

        } catch (IOException | ClassNotFoundException e) {
            //SocketTimeoutException and SocketException are caught by the IOException
            LOGGER.severe("Error: " + e.getMessage());
            throw new RegisterException("Error trying to register.");
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

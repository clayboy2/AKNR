/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networking;

import io.FileHandler;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import logging.AccessViolation;
import logging.Error;
import networking.Misc.IOBox;
import enums.Mode;

/**
 *
 * @author austen
 */
public class LoginHandler {
    private static final String hostname = "aknr.thepoisoncoin.club";
    private static final int port = 43715;
    
    public static User login(String username, String password)
    {
        ArrayList<User> users = FileHandler.getUsers();
        if (users.contains(new User(username)))
        {
            User attempt = users.get(users.indexOf(new User(username)));
            if (attempt.passwordMatches(password))
            {
                return attempt;
            }
            else
            {
                new AccessViolation("Invalid password attempt",attempt).log();
                return null;
            }
        }
        new Error("User not exist: "+username).log();
        return null;
    }
    
    public static User login(String username, String password, boolean isClientMode)
    {
        if (isClientMode)
        {
            //Need to contact main server...
            try
            {
                Socket loginServer = new Socket(hostname,port);
                IOBox io = new IOBox(loginServer);
                String status;
                synchronized (LoginHandler.class)
                {
                    io.send(username);
                    io.send(password);
                    status = io.recieve();
                }
                if (status.equalsIgnoreCase("success"))
                {
                    io.switchMode(Mode.OBJECT);
                    return (User)io.recieveObject();
                }
                else
                {
                    return null;
                }
            }
            catch (IOException e)
            {
                new Error("Error logging in",e).log();
                return null;
            }
        }
        else
        {
            return login(username, password);
        }
    }
}

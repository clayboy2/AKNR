/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networking;

import enums.Status;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import logging.Error;
import utils.User;

/**
 *
 * @author amclay2
 */
public class Connection {
    private Socket mySock;
    private User user;
    private PrintWriter outputStream;
    private BufferedReader inputStream;
    
    public Connection(Socket mySock)
    {
        try
        {
            outputStream = new PrintWriter(mySock.getOutputStream());
            inputStream = new BufferedReader(new InputStreamReader(mySock.getInputStream()));
        }
        catch (Exception e)
        {
            
        }
    }
    
    public Status send(String message)
    {
        outputStream.println(message);
        return Status.SUCCESS;
    }
    
    public String recieve()
    {
        try {
            return inputStream.readLine();
        } catch (IOException ex) {
            return null;
        }
    }
    
    public String getName()
    {
        return user.getName();
    }
    
    public boolean shouldRead()
    {
        try {
            return mySock.getInputStream().available()>0;
        } catch (IOException ex) {
            new Error("Error checking input stream",ex).log();
            return false;
        }
    }
}

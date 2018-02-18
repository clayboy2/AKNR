/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networking;

import enums.Status;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import logging.Error;

/**
 *
 * @author amclay2
 */
public class Server implements Runnable{
    //Class Variables
    private final int port;
    private volatile boolean isRunning;
    
    public Server(int port)
    {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            isRunning = true;
            ServerSocket s = new ServerSocket(port);
            while (isRunning)
            {
                Socket sock = s.accept();
                Connection c = new Connection(sock);
                NetworkController.handle(c);
            }
        } catch (IOException ex) {
            new Error("Error in server loop.",ex).log();
        }
    }
}

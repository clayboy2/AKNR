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
import java.net.ServerSocket;
import java.net.Socket;
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
            new Thread(new LoginServer()).start();
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
    
    private class LoginServer implements Runnable
    {
        private volatile boolean isRunning;
        @Override
        public void run() {
            isRunning = true;
            ServerSocket server;
            try
            {
                server = new ServerSocket(43715);
                Socket client = server.accept();
                new Thread(new Action(client)).start();
            }
            catch (Exception e)
            {
                new Error("Error handling login",e).log();
            }
        }
        
        public void stop()
        {
            isRunning = false;
        }
        
        private class Action implements Runnable
        {
            private final Socket toDo;
            private volatile Status status;
            
            public Action(Socket s)
            {
                this.toDo = s;
            }
            
            @Override
            public void run()
            {
                status = Status.RUNNING;
                String username = "Empty";
                String password;
                try
                {
                    PrintWriter out = new PrintWriter(toDo.getOutputStream());
                    BufferedReader in = new BufferedReader(new InputStreamReader(toDo.getInputStream()));
                    synchronized (this) {
                        username = in.readLine();
                        password = in.readLine();
                    }
                    User u = LoginHandler.login(username, password);
                    if (u!=null)
                    {
                        status = Status.SUCCESS;
                    }
                    else
                    {
                        status = Status.FAIL;
                    }
                }
                catch(IOException e)
                {
                    new Error("Error handling login with username: "+username,e).log();
                }
            }
            
            public synchronized Status getStatus()
            {
                return status;
            }
        }
    }
}

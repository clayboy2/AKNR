/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networking;

import enums.Status;
import java.util.ArrayList;

/**
 *
 * @author austen
 */
public class NetworkController {
    private static volatile ArrayList<Connection> allConnections;
    private static volatile Lobby mainLobby;
    private static volatile ArrayList<Lobby> sideLobbies;
    
    public Status init(Connection serverConn)
    {
        allConnections = new ArrayList<>();
        mainLobby = new Lobby(serverConn);
        sideLobbies = new ArrayList<>();
        return Status.SUCCESS;
    }
    
    public static Status handle(Connection c)
    {
        allConnections.add(c);
        String toDo = c.recieve(); //First contact from client
        switch (toDo)
        {
            case "connect_chat":
                break;
            default:
                break;
        }
        return Status.SUCCESS;
    }
    
    private class Lobby implements Runnable
    {
        private volatile ArrayList<Connection> members;
        private volatile Connection host;
        private volatile boolean isRunning;
        
        public Lobby(Connection host)
        {
            this.host = host;
            members = new ArrayList<>();
            members.add(host);
        }
        
        public Status setHost(Connection c)
        {
            this.host = c;
            return Status.SUCCESS;
        }
        
        public Status removeConnection(Connection c)
        {
            members.remove(c);
            if (c.equals(host))
            {
                setHost(members.get(0));
                host.send("You are the new host!");
            }
            sendAll(c.getName()+" has left the lobby");
            return Status.SUCCESS;
        }
        
        private Status sendAll(String message)
        {
            for (Connection c : members)
            {
                c.send(message);
            }
            return Status.SUCCESS;
        }
        
        public Status addConnection(Connection c)
        {
            members.add(c);
            return Status.SUCCESS;
        }

        @Override
        public void run() {
            isRunning = true;
            while (isRunning)
            {
                for (Connection c : members)
                {
                    if (c.shouldRead())
                    {
                        String response = c.recieve();
                        switch(response)
                        {
                            
                        }
                    }
                }
            }
        }
        
        public Status stop()
        {
            isRunning = false;
            return Status.SUCCESS;
        }
    }
}

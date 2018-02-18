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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import logging.Error;
import enums.Mode;

/**
 *
 * @author austen
 */
public class Misc {

    public static class IOBox {

        private final Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private ObjectOutputStream objectOut;
        private ObjectInputStream objectIn;
        private Mode mode;

        public IOBox(Socket socket) {
            this.socket = socket;
            try {
                out = new PrintWriter(socket.getOutputStream());
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                new Error("Error getting streams...", e).log();
            }
            mode = Mode.SIMPLE;
        }

        public IOBox(Socket socket, Mode mode) {
            this.socket = socket;
            switch (mode) {
                case OBJECT:
                    try {
                        objectOut = new ObjectOutputStream(socket.getOutputStream());
                        objectIn = new ObjectInputStream(socket.getInputStream());
                    } catch (IOException ex) {
                        Logger.getLogger(Misc.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    this.mode = Mode.OBJECT;
                    break;
                case SIMPLE:
                    try {
                        out = new PrintWriter(socket.getOutputStream());
                        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    } catch (IOException e) {
                        new Error("Error getting streams...", e).log();
                    }
                    this.mode = Mode.SIMPLE;
                    break;
                default:
                    break;
            }
        }

        public void send(String message) {
            out.println(message);
        }

        public String recieve() {
            String response;
            try {
                response = in.readLine();
            } catch (IOException e) {
                new Error("Error recieving ", e).log();
                response = "Error recieving";
            }
            return response;
        }
        
        public Status sendObject(Object object)
        {
            try {
                objectOut.writeObject(object);
            } catch (IOException ex) {
                new Error("Error sending object",ex).log();
                return Status.FAIL;
            }
            return Status.SUCCESS;
        }
        
        public Object recieveObject()
        {
            try
            {
                return objectIn.readObject();
            }
            catch (IOException | ClassNotFoundException e)
            {
                new Error("Error recieving object").log();
                return null;
            }
        }

        public Status switchMode(Mode mode) {
            if (this.mode == mode) {
                //Nothing to do
                return Status.SUCCESS;
            }
            switch (mode) {
                case OBJECT:
                    try {
                        objectOut = new ObjectOutputStream(socket.getOutputStream());
                        objectIn = new ObjectInputStream(socket.getInputStream());
                    } catch (IOException ex) {
                        Logger.getLogger(Misc.class.getName()).log(Level.SEVERE, null, ex);
                        this.mode = Mode.ERROR;
                        return Status.FAIL;
                    }
                    this.mode = Mode.OBJECT;
                    break;
                case SIMPLE:
                    try {
                        out = new PrintWriter(socket.getOutputStream());
                        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    } catch (IOException e) {
                        new Error("Error getting streams...", e).log();
                        this.mode = Mode.ERROR;
                        return Status.FAIL;
                    }
                    this.mode = Mode.SIMPLE;
                    break;
                default:
                    this.mode = Mode.ERROR;
                    return Status.FAIL;
            }
            return Status.SUCCESS;
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import enums.Status;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import utils.User;

/**
 *
 * @author austen
 */
public class FileHandler {
    public static void write(Object data, File file)
    {
        try
        {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(data);
        }
        catch(Exception e)
        {
            
        }
    }
    
    public static Object read(File file)
    {
        try
        {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            return in;
        }
        catch (Exception e)
        {
            return null;
        }
    }
    
    public static ArrayList<User> getUsers()
    {
        return (ArrayList<User>)read(new File("resources/users.bin"));
    }
    
    public static Status writeUsers(ArrayList<User> toUpdate)
    {
        ArrayList<User> allUsers = getUsers();
        allUsers.removeAll(allUsers);
        allUsers.addAll(allUsers);
        return Status.SUCCESS;
    }
}

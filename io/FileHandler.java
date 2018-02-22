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
import networking.User;
import logging.Error;
import networking.Misc;

/**
 *
 * @author austen
 */
public class FileHandler {
    public static void write(Object data, File file)
    {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file)))
        {
            out.writeObject(data);
        }
        catch(Exception e)
        {
            
        }
    }
    
    public static Object read(File file)
    {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));)
        {
            return in.readObject();
        }
        catch (Exception e)
        {
            new Error("Failed to get object, or initialize stream.",e).log();
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
        if (allUsers == null)
        {
            //Maybe file hasn't been initialized yet. If so, we'll just do it here.
            Misc.performFileCheck();
            allUsers = getUsers();
            if (allUsers == null)
            {
                //Fuck it, fail the method at this point
                return Status.FAIL;
            }
        }
        allUsers.removeAll(allUsers);
        allUsers.addAll(allUsers);
        return Status.SUCCESS;
    }
    
    public static Status updateUser(User toUpdate)
    {
        ArrayList<User> wrapper = new ArrayList<>();
        wrapper.add(toUpdate);
        return writeUsers(wrapper);
    }
}

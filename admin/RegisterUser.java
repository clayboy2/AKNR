/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import containers.ContactInfo;
import enums.Status;
import io.FileHandler;
import java.util.ArrayList;
import logging.Log;
import networking.User;

/**
 *
 * @author austen
 */
public class RegisterUser {
    public static Status createUser(String name, String username, String password, String email, ContactInfo contactInfo)
    {
        User u = new User(name, username, password, email, contactInfo);
        if (isExistingUser(u))
        {
            return Status.SUCCESS;
        }
        ArrayList<User> wrapper = new ArrayList<>();
        wrapper.add(u);
        FileHandler.writeUsers(wrapper);
        return Status.SUCCESS;
    }
    
    private static boolean isExistingUser(User u)
    {
        ArrayList<User> existingUsers = FileHandler.getUsers();
        if (existingUsers.contains(u))
        {
            new Log("Username: "+u.getName()+" already exists").log();
            return true;
        }
        return false;
    }
}

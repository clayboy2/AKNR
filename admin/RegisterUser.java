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
        ArrayList<User> existingUsers = FileHandler.getUsers();
        if (existingUsers.contains(u))
        {
            new Log("Username: "+username+" already exists").log();
            return Status.FAIL;
        }
        ArrayList<User> wrapper = new ArrayList<>();
        FileHandler.writeUsers(wrapper);
        return Status.SUCCESS;
    }
}

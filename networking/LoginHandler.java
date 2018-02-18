/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networking;

import io.FileHandler;
import java.util.ArrayList;
import logging.AccessViolation;
import utils.User;
import logging.Error;

/**
 *
 * @author austen
 */
public class LoginHandler {
    
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
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import networking.User;

/**
 *
 * @author austen
 */
public class CurrentUser {
    private static volatile User currentUser;
    
    public static User getUser()
    {
        return currentUser;
    }
    
    public static void setUser(User user)
    {
        currentUser = user;
    }
}

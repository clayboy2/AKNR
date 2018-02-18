/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import content.Post;
import java.util.Date;

/**
 *
 * @author amclay2
 */
public class User {
    private String name;
    private String username;
    private String password;
    private Date dateJoined;
    private String email;
    private ContactInfo contactInfo;
    private AccessLevel accessLevel;
    //Track posts in program
    //Track vehicles in program
    //Track events in program
    
    public User(String name, String username, String password, String email, ContactInfo contactInfo)
    {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.contactInfo = contactInfo;
        this.accessLevel = AccessLevel.MEMBER;
    }
    
    public AccessLevel getAccessLevel()
    {
        return accessLevel;
    }
    
    public void setAccessLevel(AccessLevel newLevel)
    {
        this.accessLevel = newLevel;
    }
}

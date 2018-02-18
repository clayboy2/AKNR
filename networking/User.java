/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networking;

import containers.ContactInfo;
import enums.AccessLevel;
import java.util.Date;
import java.util.Objects;

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
    
    public User(String name) //Only to be used for logging in
    {
        this.username = name;
    }
    
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
    
    public String getName()
    {
        return this.username;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof User))
        {
            return false;
        }
        return (this.username.equalsIgnoreCase(((User)o).username));
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.username);
        return hash;
    }

    public boolean passwordMatches(String password) {
        return password.equals(this.password);
    }
}

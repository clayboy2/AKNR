/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package content;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import security.AccessViolation;
import utils.AccessLevel;
import utils.User;

/**
 *
 * @author amclay2
 */
public class Event {
    private Date eventDate;
    private String title;
    private String description;
    private File picture;
    private ArrayList<User> roster;
    private AccessLevel minimumAccess;
    private User host;
    
    public Event(Date eventDate, String title, User host)
    {
        this.eventDate = eventDate;
        this.title = title;
        this.description = "No description set";
        this.roster = new ArrayList<>();
        this.host = host;
        roster.add(host);
        minimumAccess = AccessLevel.NOOB;
    }
    
    public Event(Date eventDate, String title, String description, User host, File picture, AccessLevel minimumAccess)
    {
        this.eventDate = eventDate;
        this.title = title;
        this.description = description;
        this.host = host;
        this.roster = new ArrayList<>();
        roster.add(host);
        this.minimumAccess = minimumAccess;
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public File getPicture()
    {
        return picture;
    }
    
    public Date getEventDate()
    {
        return eventDate;
    }
    
    public ArrayList<User> getRoster()
    {
        return roster;
    }
    
    public User getHost()
    {
        return host;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    public void setPicture(File picture)
    {
        this.picture = picture;
    }
    
    public void addUserRoster(User toAdd)
    {
        if (AccessLevel.hasAccess(toAdd, minimumAccess))
        {
            roster.add(toAdd);
        }
        else
        {
            new AccessViolation("User attempted to RSVP event they didn't have access for",toAdd).log();
        }
    }
    
    public void removeUserRoster(User toRemove)
    {
        roster.remove(toRemove);
    }
}

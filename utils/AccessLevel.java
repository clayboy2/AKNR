/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import security.AccessViolation;
import security.InvalidOperation;

/**
 *
 * @author amclay2
 */
public enum AccessLevel {
    NOOB (0),
    MEMBER(1),
    MODERATOR(2),
    ADMIN(3),
    DEVELOPER(4);
    
    private int accessLevel;
    
    AccessLevel(int level)
    {
        this.accessLevel = level;
    }
    
    public void raiseAccess(User toRaise, User authority, AccessLevel raiseTo)
    {
        switch (authority.getAccessLevel()) {
            case ADMIN:
                if (raiseTo == DEVELOPER)
                {
                    new AccessViolation("Attempt to raise to Dev privs. Not allowed", authority).log();
                }
                else if (toRaise.getAccessLevel()==DEVELOPER||toRaise.getAccessLevel()==ADMIN)
                {
                    new InvalidOperation("Authority cannot raise to a higher access level").log();
                }
                else if (raiseTo.getAccessInt()<toRaise.getAccessLevel().getAccessInt())
                {
                    new InvalidOperation("Attempt to lower users access level.").log();
                }
                toRaise.setAccessLevel(raiseTo);
                break;
            case DEVELOPER:
                if (raiseTo.getAccessInt()<toRaise.getAccessLevel().getAccessInt())
                {
                    new InvalidOperation("Attempted to lower access levels.").log();
                }
                toRaise.setAccessLevel(raiseTo);
                break;
            default:
                new AccessViolation("Authority does not have access to raise access levels",authority).log();
                break;
        }
    }
    
    private int getAccessInt()
    {
        return accessLevel;
    }
    
    public static boolean hasAccess(User attempt, AccessLevel minimumAccessLevelRequired)
    {
        if (attempt.getAccessLevel().getAccessInt()<minimumAccessLevelRequired.getAccessInt())
        {
            new AccessViolation("User does not have access to operations",attempt).log();
            return false;
        }
        return true;
    }
    
    //Priveleges:
    /*
    * Noob: (Day Drifter)
    * --Chat
    * --View Posts
    *
    * Member: (Night Rider)
    * --Post content
    * --View Events
    * 
    * Moderators: (Club Leader)
    * --Create Events
    * --Remove Noobs
    *
    * Administrator: (Club Owner)
    * --Full Access
    *
    * Developer: (Club Super Nerd)
    * --Push Updates
    * --Server Access
    */
}

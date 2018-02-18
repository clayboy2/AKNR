/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logging;

import utils.User;

/**
 *
 * @author amclay2
 */
public class AccessViolation {
    private final User violator;
    private final String message;
    
    public AccessViolation(String message, User violator)
    {
        this.message = message;
        this.violator = violator;
    }
    
    public User getViolator()
    {
        return this.violator;
    }
    
    public String getMessage()
    {
        return this.message;
    }
    
    public void log()
    {
        
    }
}

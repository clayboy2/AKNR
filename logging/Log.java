/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logging;

/**
 *
 * @author austen
 */
public class Log implements Loggable{
    private final String message;
    
    public Log(String message)
    {
        this.message = message;
    }
    
    public String getMessage()
    {
        return message;
    }
    
    @Override
    public void log() {
        
    }
    
}

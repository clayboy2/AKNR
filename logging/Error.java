/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logging;

/**
 *
 * @author amclay2
 */
public class Error implements Loggable{
    private final String message;
    private Exception e;
    
    public Error(String message)
    {
        this.message = message;
    }
    
    public Error(String message, Exception e)
    {
        this.message = message;
        this.e = e;
    }

    @Override
    public void log() {
    }
}

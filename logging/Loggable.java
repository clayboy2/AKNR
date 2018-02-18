/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logging;

import java.io.Serializable;

/**
 *
 * @author amclay2
 */
public interface Loggable extends Serializable{
    public void log();
}

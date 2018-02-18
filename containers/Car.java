/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package containers;

import java.io.File;
import networking.User;

/**
 *
 * @author amclay2
 */
public class Car {
    private int year;
    private String make;
    private String model;
    private File picture;
    private String nickname;
    private User owner;
    
    public Car(String make, String model, int year, String nickname, User owner)
    {
        this.year = year;
        this.model = model;
        this.make = make;
        this.nickname = nickname;
        this.owner = owner;
    }
    
    public String getMake()
    {
        return make;
    }
    
    public String getModel()
    {
        return model;
    }
    
    public int getYear()
    {
        return year;
    }
    
    public File getPicture()
    {
        return picture;
    }
    
    public String getNickName()
    {
        return nickname;
    }
    
    public void setPicture(File file)
    {
        this.picture = file;
    }
    
    public void setYearMakeModel(int year, String make, String model)
    {
        this.year = year;
        this.model = model;
        this.make = make;
    }
    
    public void setNickName(String nickname)
    {
        this.nickname = nickname;
    }
    
    public User getOwner()
    {
        return owner;
    }
}

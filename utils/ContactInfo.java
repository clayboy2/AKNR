/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author amclay2
 */
public class ContactInfo {
    private String number;
    private String email;
    private String address;
    
    public ContactInfo(String number, String email)
    {
        this.email = email;
        this.number = number;
    }
    
    public ContactInfo(String number, String email, String address)
    {
        this.email = email;
        this.number = number;
        this.address = address;
    }
    
    public String getNumber()
    {
        return number;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public String getAddress()
    {
        return address;
    }
    
    public void setNumber(String number)
    {
        this.number = number;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public void setAddress(String address)
    {
        this.address = address;
    }
}

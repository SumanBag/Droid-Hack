/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package droidhack;

/**
 *
 * @author SUMAN
 */
public class StatusException extends Exception{
    
    private String msg;
    
    StatusException()
    {
        msg="Header Not Supported";
    }
    
    @Override
    public String toString()
    {
        return msg;
    }
    
    @Override
    public void printStackTrace()
    {
        this.getCause().printStackTrace();
    }
}

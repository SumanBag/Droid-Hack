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

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class DroidCommHndlr{
    
    private Socket sc;
    private DataInputStream in;
    private DataOutputStream out;
    private byte[] sndH,sndD,rcvH,rcvD;
 
    
    DroidCommHndlr(Socket a) throws Exception
    {  
         sc=a;
         //System.out.println("Connected..");
         //System.out.println("Client: "+sc.getInetAddress()+":"+sc.getPort());
         in=new DataInputStream(sc.getInputStream());
         out=new DataOutputStream(sc.getOutputStream());
         sndH=new byte[4];
         rcvH=new byte[4];
      
    }
    
    private int readH()throws Exception
    {
        int a;
        in.read(sndH);
        if(sndH[2]!=0)
           throw new StatusException();    
        a=sndH[3];
        return a;
    }
    
    private byte[] readD(int a)throws Exception
    {
        byte[] data=new byte[a];
        rcvD=new byte[a+4];
        in.read(rcvD);
        for(int i=0;i<a;i++)
        {
            data[i]=rcvD[i+4];
        }
        return data;
    }
    
    public String readString()throws Exception
    {
        String msg="";
        int a;
        a=readH();
        byte[] b=readD(a);
        
        for(byte k:b)
            msg+=(char)k;
        
        return msg;
    }
    
    public byte readByte()throws Exception
    {
        readH();
        byte[] a=readD(1);
        return a[4];  
    }
    
    private void sendH(int a)throws Exception
    {
        sndH[3]=(byte)a;
        out.write(sndH);    
    }
    
    private void sendD(byte[] d)throws Exception
    {
        int a=d.length;
        sndD=new byte[a+4];
        System.arraycopy(d, 0, sndD, 4, a);
        out.write(sndD);
    }
    
    public void sendString(String msg)throws Exception
    {
        msg+="\r\n";
        byte[] a=msg.getBytes();
        sendH(msg.length());
        sendD(a);   
    }
    
    public void sendByte(byte a)throws Exception
    {   
        byte[] d={a};
        sendH(1);
        sendD(d);   
    }
    
    public void close()throws Exception
    {
       
            out.close();
            in.close();
       
    }
    
   
    
    public int readAvailable()throws Exception
    {
        return in.available();
    }
   
    
}

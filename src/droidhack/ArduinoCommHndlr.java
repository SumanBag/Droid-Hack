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
import com.fazecast.jSerialComm.SerialPort;
import java.io.InputStream;
import java.io.OutputStream;
public class ArduinoCommHndlr implements Runnable{
    
    private SerialPort dev;
    private SerialPort[] coms;
    private int port;
    private Thread comscan;
    private DroidCommHndlr ob;
    private boolean stop;
    private InputStream in;
    private OutputStream out;
    ArduinoCommHndlr(DroidCommHndlr a)
    {
        ob=a;
        stop=false;
        
    }
    
    public void run()
    {
        try
        {
            while(dev.isOpen())
            {
                if(ob.readAvailable()>0)
                    dev.writeBytes(buffer,)
            }
            
            ob.close();
            in.close();
            out.close();
            dev.closePort();
        }catch(Exception e)
        {
            System.err.println(e);
            
        }
        
    }
    
    
    public void comscan()
    {
        coms=SerialPort.getCommPorts();
        System.out.print("\nComm Ports:\n");
        for(int i=0;i<coms.length;i++)
            System.out.print((i+1)+": "+coms[i].getDescriptivePortName());
        System.out.println("[@] Select Device: ");
                
    }
    
    public void commport(int i)
    {
        if(i>0&&i<=coms.length)
            dev=coms[i-1];
        in=dev.getInputStream();
        out=dev.getOutputStream();
        
            
    }
    
}

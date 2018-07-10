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

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.Scanner;

public class DeamonServer implements Runnable{
    
    private Vector<DeamonHndlr> clients;
    private ServerSocket server;
    private Socket client;
    private Scanner kb;
    private Thread T;
    private DeamonHndlr ob;
    
    DeamonServer(Scanner a)
    {
        try{
        kb=a;
        server=new ServerSocket(5555);
        clients=new Vector<DeamonHndlr>();
        T=new Thread(this);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }
    
    public void run()
    {
        while(!server.isClosed())
        {
            try{
            client=server.accept();
            //ob=new DeamonHndlr(client);
            System.out.println("");
                    
            clients.add(new DeamonHndlr(this,client));
            
            }catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }
            
    public void startServer()
    {
        T.start();
        
    }
    
    public void removeClient(DeamonHndlr ob)
    {
        clients.remove(ob);
    }
    
}

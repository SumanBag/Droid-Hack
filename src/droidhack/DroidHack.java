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
import java.util.Scanner;

public class DroidHack implements Runnable{

    private ServerSocket srvr;
    private Socket sc;
    private Scanner kb;
    private Thread T;
    private DroidCommHndlr ob;
    private Controler cntrlr;
    private String cmm;
    DroidHack()
    {
        kb=new Scanner(System.in);
        T=new Thread(this);
        T.setPriority(Thread.MAX_PRIORITY);
        
          
    }
    
    private void slctMode()
    {
        System.out.println("\n..............DROID DEAMON..............");
        System.out.println("Select Mode:");
        System.out.println("  1: DEAMON SERVER");
        System.out.println("  2: DROID Controler");
        System.out.println("  3: DROID HOST");
        System.out.println("----------------------------------------");
        System.out.print("> ");
        int a=kb.nextInt();
        switch(a)
        {
            case 1: startServer();
                    startControler();
                   break;
            case 2: connServer();
                   break;
            case 3: 
                   break;
            default: System.out.print("[@] Wrong Operation!\n> ");
            
                    
        }
    }
    
    public void console()
    {
        slctMode(); 
        do{
            System.out.print("> ");
            cmm=kb.next();
            if(cmm.equals("show"))
                System.out.print("[@] hello\n");
                
        }while(!cmm.equals("quit"));
        System.out.println("Good Bye..");
    }
    
    private void startServer()
    {
        System.out.print("[@] Select port: ");
        int a=kb.nextInt();
        System.out.println("[@] Server Online..(port:"+a+")");
        try{
            srvr=new ServerSocket(a);
            sc=srvr.accept();
            System.out.println("[@] Client: "+sc.getInetAddress()+":"+sc.getPort());
            System.out.println("[--------------------------------------]");
            ob=new DroidCommHndlr(sc);
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }
    
    private void connServer()
    {
        System.out.print("[@] IP: ");
        String ip=kb.next();
        System.out.print("[@] Port: ");
        int a=kb.nextInt();
        try{
            sc=new Socket(ip,a);
            System.out.println("[@] Connected.");
            ob=new DroidCommHndlr(sc);
         
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }
    
    private void startControler()
    {
        cntrlr=new Controler(ob);
        cntrlr.setVisible(true);
        
    }
    public void run()
    {
      while(!sc.isClosed()){
          try{
            
                 while(ob.readAvailable()==0)
                 {      
                   //System.out.print(".");
                   Thread.sleep(100);
                 }
               System.out.print("[@] Message: "+ob.readString());
             
        }catch(StatusException e)
        {
            e.toString();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
      }
      System.out.println("Good Bye..");
    }
   
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        new DroidHack().console();
        //new DroidHack().startControler();
        
    }
    
}

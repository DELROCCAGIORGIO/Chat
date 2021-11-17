/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import java.io.*;
import java.net.*;
import java.util.*;


/**
 *
 * @author Giorgio Del Rocca
 */
public class MultiServer 
{
 public void start()
 {
     AppClients m=new AppClients();
     try
     {
         ServerSocket serverSocket=new ServerSocket(6789);
         for(;;)
         { 
         System.out.println("1 SEREVER in attesa...");
         Socket socket=serverSocket.accept();
         System.out.println("3 Server socket"+socket);
         ServerThread serverThread= new ServerThread(socket,serverSocket,m);
         serverThread.start();
         }
     }
     catch(Exception e)
     {
         System.out.println(e.getMessage());
         System.out.println("Errore durante l'istanza del server!");
         System.exit(1);
     }
    }
}


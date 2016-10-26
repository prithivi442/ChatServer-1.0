/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prithivi.JavaChatServer;

import com.prithivi.JavaChatServer.handler.ClientListener;
import com.prithivi.JavaChatServer.handler.RequestHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Prithivi Raj
 */
public class Program {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
        ServerSocket server = new ServerSocket(9000);
        RequestHandler handler = new RequestHandler();
        System.out.println("server is running at port 9000");
        while(true){
            
            Socket client = server.accept();
            System.out.println("receiving request from : "+client.getInetAddress().getHostAddress()); 
            ClientListener listener = new ClientListener(client, handler);
            listener.start();
        }
        }
        
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prithivi.JavaChatServer.handler;

import com.prithivi.JavaChatServer.entity.Client;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Prithivi Raj
 */
public class RequestHandler {
    List<Client> clients = new ArrayList<>();
    
    public void addClient(Client client){
        clients.add(client);
    } 

    public List<Client> getClient(){
        return clients;
    }
    
    public Client getByUsername(String username){
        for(Client c:clients){
            if(c.getUsername().equals(username)){
                return c;
            }
            
        }
        return null;
    }
     public Client getBySocket(Socket socket){
        for(Client c:clients){
            if(c.getSocket().equals(socket)){
                return c;
            }
            
        }
        return null;
    }
     public void broadcastMessage(Client client, String message) throws IOException{
         for(Client c: clients){
             if(c.getSocket().equals(client)){
                 PrintStream ps = new PrintStream(c.getSocket().getOutputStream());
                 ps.println(message);
                
             }
         }
     }
}

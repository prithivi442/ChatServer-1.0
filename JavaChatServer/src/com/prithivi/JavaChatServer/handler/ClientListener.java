/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prithivi.JavaChatServer.handler;

import com.prithivi.JavaChatServer.UserDAO.DaoIMPL;
import com.prithivi.JavaChatServer.UserDAO.UserDAO;
import com.prithivi.JavaChatServer.entity.Client;
import com.prithivi.JavaChatServer.entity.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author Prithivi Raj
 */
public class ClientListener extends Thread {

    private Socket client;

    public ClientListener(Socket client, RequestHandler handler) {
        this.client = client;
        this.handler = handler;
    }
    PrintStream ps;
    BufferedReader reader;
    String username, password;
    UserDAO userDAO;
    private RequestHandler handler;

    @Override
    public void run() {
        userDAO = new DaoIMPL();
        try {
            ps = new PrintStream(client.getOutputStream());
            handler = new RequestHandler();
//            ps.println("*****WELCOME TO PRIVATE CHATSERVER*****");
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            while (!doLogin()) {
                ps.println("Invalid Username and Password, Please Try Again!!");
            }
            doChat();
//            while (!doChat()) {
//                while (!doLogin()) {
//                    ps.println("Invalid Username and Password, Please Try Again!!");
//                    doChat();
//                }
//            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public boolean doLogin() throws IOException {
//        do{
        ps.println("Enter username : ");
        username = reader.readLine();
        ps.println("Enter password : ");
        password = reader.readLine();
        User user = userDAO.Login(username, password);
        if (user != null) {
            handler.addClient(new Client(username, client));
            return true;
        }
        
        return false;
//            }
//            while(password == null && username == null);
//            ps.println("thankyou "+username);

    }

    public void doChat() throws IOException {
        ps.println("*****WELCOME TO PRIVATE CHAT ROOM*****");
        ps.println("You can start messaging now.");
        while (true) {
            String line = reader.readLine();
            Client sender = handler.getBySocket(client);
            if (line.equalsIgnoreCase("exit")) {
                break;
            }
            handler.broadcastMessage(sender, "sender.getUsername() says >>>" +line);

        }
        ps.println("You are out of Chat Room. Thank You!!.");
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prithivi.JavaChatServer.UserDAO;

import com.prithivi.JavaChatServer.entity.Client;
import com.prithivi.JavaChatServer.entity.User;
import java.util.List;

/**
 *
 * @author Prithivi Raj
 */
public class DaoIMPL implements UserDAO{
    
    @Override
    public User Login(String username, String password) {
        User u = new User();
        u.setUsername(username);
        u.setPassword(password);
        if(u.getUsername().equals("client") && u.getPassword().equals("youcanpass")){
            return u;
        }
        
        return null;
        
    }



    
}

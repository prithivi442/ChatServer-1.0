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
public interface UserDAO {
    User Login(String username, String password);

}

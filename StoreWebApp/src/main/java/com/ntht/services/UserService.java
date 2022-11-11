/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ntht.services;

import com.ntht.pojos.User;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author Acer
 */
public interface UserService extends UserDetailsService{
    User getUserById(int userId);
    boolean addUser(User user);
    List<User> getUsers(String username);
}

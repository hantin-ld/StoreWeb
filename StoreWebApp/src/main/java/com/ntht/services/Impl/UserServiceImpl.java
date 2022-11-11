/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntht.services.Impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ntht.pojos.User;
import com.ntht.repositorys.UserRepository;
import com.ntht.services.UserService;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Acer
 */
@Service("userDetailsService")
public class UserServiceImpl implements UserService{
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private Cloudinary cloudinary;
    
    @Autowired
    private UserRepository userRepository;
    
    
    @Override
    public User getUserById(int userId) {
        return this.userRepository.getUserById(userId);
    }

    @Override
    public boolean addUser(User user) {
        
        try {
            String paas = user.getPassword();
            user.setPassword(this.passwordEncoder.encode(paas));
            user.setUserRole(User.USER);
            
            Map r = this.cloudinary.uploader().upload(user.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
            user.setAvatar((String) r.get("secure_url"));
            
            return this.userRepository.addUser(user);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false; 
    }

    @Override
    public List<User> getUsers(String username) {
        return this.userRepository.getUsers(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> users = this.getUsers(username);
        if(users.isEmpty()){
            throw new UsernameNotFoundException("Tài khoản không tồn tại !!!");
        }
        User u = users.get(0);
        Set<GrantedAuthority> auth = new HashSet<>();
        auth.add(new SimpleGrantedAuthority(u.getUserRole()));
        return new org.springframework.security.core.userdetails.User(u.getUsername(), u.getPassword(), auth);
    }
    
}

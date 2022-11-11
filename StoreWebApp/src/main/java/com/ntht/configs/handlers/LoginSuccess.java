/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntht.configs.handlers;



import com.ntht.pojos.User;
import com.ntht.services.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 *
 * @author Acer
 */
@Component
public class LoginSuccess implements AuthenticationSuccessHandler{
    
    @Autowired
    private UserService userDetailsService;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication a) throws IOException, ServletException {
        User user = this.userDetailsService.getUsers(a.getName()).get(0);
        request.getSession().setAttribute("currentUser", user);
        response.sendRedirect("/StoreWebApp");
    }
    
}

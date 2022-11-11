/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntht.controllers;

import com.ntht.pojos.User;
import com.ntht.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Acer
 */
@Controller
public class UserController {
    @Autowired
    private UserService userDetailsService;
    
    @GetMapping("/login")
    public String login(){
        return "login-page";
    }
    
    @GetMapping("/register")
    public String registerView(Model model){
        model.addAttribute("user", new User());
        return "register-page";
    }
    
    @PostMapping("/register")
     public String register(Model model, @ModelAttribute(value = "user") User user){
        // kiểm tra lỗi bằng bean validater @valit thêm message + bindingResult bắt lỗi
        
        if(user.getPassword().isEmpty() || !user.getConfirmPassword().equals(user.getPassword())){
            model.addAttribute("errMsg", "Mật khẩu không trùng khớp !!!");
        }else{
            if (this.userDetailsService.addUser(user) == true) {
                return "redirect:/login";
            }
            model.addAttribute("errMsg", "Đã có lỗi xảy ra khi tạo tài khoản, vui long quay lại sau !!!");
        }
        
        return "register-page";
    }
}

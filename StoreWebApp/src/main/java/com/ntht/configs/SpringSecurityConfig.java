/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntht.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ntht.configs.handlers.LoginSuccess;
import com.ntht.configs.handlers.LogoutSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author Acer
 */
@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.ntht.repositorys",
    "com.ntht.services"
})
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationSuccessHandler loginSuccessHandler;
    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;
    
    @Bean // phương thức bâm mật khẩu
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationSuccessHandler loginSuccessHandler(){
        return new LoginSuccess();
    }
    
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler(){
        return new LogoutSuccess();
    }
    
    
     // tạo bean Cloudinary
    @Bean // tài khoản Nguyentin15022000@gmail.com Tins.27 // hiện đang lỗi 
    public Cloudinary cloudinary(){
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dcna3kgbj",
                "api_key", "619551994853416",
                "api_secret", "z8lJc2XnQj2TUGhGMmlYYESdvCQ",
                "secure", true));
        return cloudinary;
    }
    
    // Cung cấp thông tin chứng thực
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    
     // Thông tin phân quyền
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // cấu hình thiết lập view.jsp liên kết form
        http.formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password");
        
        // cấu hình khi thành công / thất bại: thành công về trang chủ / thất bại đứng tại trang đăng nhập
        http.formLogin().defaultSuccessUrl("/").failureUrl("/login?error");
        http.formLogin().successHandler(this.loginSuccessHandler);
        
        // cấu hình đăng xuất: đăng xuất thành công quay về trang đăng nhập // hoặc cho quay về trang chủ
        http.logout().logoutSuccessUrl("/login");
        http.logout().logoutSuccessHandler(this.logoutSuccessHandler);
        
        //báo lỗi ko có quyền truy cập
        http.exceptionHandling().accessDeniedPage("/login?accessDenied");
//        
//        // Phân quyền

        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')");
//                .antMatchers("/ad/**").access("hasRole('ROLE_ADMIN')")
//                .antMatchers("/u/**").access("hasRole('ROLE_USER')");
//                .antMatchers("/admin/**").access("hasRole('admin')");

        //csrf() -> security bảo vệ tránh người dùng chèn mã độc vào form
        // mình làm dịch vụ gửi lên không làm form
        http.csrf().disable();
    }
}

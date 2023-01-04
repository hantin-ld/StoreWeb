/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntht.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ntht.formatter.CategoryFormatter;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 *
 * @author Acer
 */
@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.ntht.controllers",
    "com.ntht.repositorys",
    "com.ntht.services"
//    "com.ntht.validator"
})
public class WebApplicationContextConfig implements WebMvcConfigurer{
    
    @Override // định nghĩa configurer enable -> cần gì thì kích hoạt sau 
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
         configurer.enable();
    }
    
    @Override // lấy tài nguyên tĩnh
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/resource/css/");
        registry.addResourceHandler("/images/**").addResourceLocations("/resource/images/");
        registry.addResourceHandler("/js/**").addResourceLocations("/resource/js/");
    }
    
    @Override
    public void addFormatters(FormatterRegistry registry){
        registry.addFormatter(new CategoryFormatter());
    }
    
    @Bean // view Resolver đơn giản nhất lấy file.jsp
    public InternalResourceViewResolver getInternalResourceViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    
    @Bean // để xuất thông tin trong resource 
    public MessageSource messageSource(){
        ResourceBundleMessageSource resource = new ResourceBundleMessageSource();
        resource.setBasename("messages");
        //resource.setBasenames("messages1", "messages2"); có nhiều file
        return resource;
    }
    
//    @Bean // tài khoản Nguyentin15022000@gmail.com Tins.27 // hiện đang lỗi 
//    public Cloudinary cloudinary(){
//        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
//                "cloud_name", "dcna3kgbj",
//                "api_key", "619551994853416",
//                "api_secret", "z8lJc2XnQj2TUGhGMmlYYESdvCQ",
//                "secure", true));
//        
//        return cloudinary;
//    }
    
    @Bean // bean để nhận biết Multipart request
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        return resolver;
    }
    
}

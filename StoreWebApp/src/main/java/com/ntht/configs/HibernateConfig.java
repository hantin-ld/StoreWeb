/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntht.configs;

import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import static org.hibernate.cfg.Environment.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
/**
 *
 * @author Acer
 */
@Configuration
@PropertySource("classpath:database.properties")
public class HibernateConfig {
    @Autowired
    private Environment environment;
    
    @Bean // bean gọi sessionFactory
    public LocalSessionFactoryBean getSessionFactory(){
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setPackagesToScan("com.ntht.pojos");
        
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setHibernateProperties(hibernateProperties());
        
        return sessionFactory;
    }
    
    @Bean // bean đọc lớp  database.property từ resource 
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("hibernate.connection.driverClass"));
        dataSource.setUrl(environment.getProperty("hibernate.connection.url"));
        dataSource.setUsername(environment.getProperty("hibernate.connection.username"));
        dataSource.setPassword(environment.getProperty("hibernate.connection.password"));
        return dataSource;
    }
    
    // lớp Driver
    private Properties hibernateProperties(){
        Properties properties = new Properties();
        properties.setProperty(DIALECT, environment.getProperty("hibernate.dialect"));
        properties.setProperty(SHOW_SQL, environment.getProperty("hibernate.showSql"));
        return properties;
    }
    
    @Bean // bean dựng transaction (giao tác)
    public HibernateTransactionManager transactionManager(){
        HibernateTransactionManager h = new HibernateTransactionManager();
        h.setSessionFactory(getSessionFactory().getObject());
        return h;
    }
}

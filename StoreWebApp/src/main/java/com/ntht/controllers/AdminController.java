/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntht.controllers;

import com.ntht.services.StatisticService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Acer
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private StatisticService statisticService;
    
    @GetMapping("/category-statistic")
    public String categoryStatis(Model model){
        model.addAttribute("cateStatis", this.statisticService.cateStatis());
        return "category-statistic";
    }
    
    @GetMapping("/product-statistic")
    public String productStatis(Model model, @RequestParam(required = false) Map<String, String> params){
        SimpleDateFormat f = new SimpleDateFormat("yyy-MM-dd");
        
        String keyWord = params.getOrDefault("kw", null);
        
        Date fromDate = null, toDate = null;
        
        try{
            String fDate = params.getOrDefault("fromDate", null);
            if (fDate != null) {
                fromDate = f.parse(fDate);
            }
            String tDate = params.getOrDefault("toDate", null);
            if (fDate != null) {
                toDate = f.parse(tDate);
            }
        }catch(ParseException ex){
            ex.printStackTrace();
        }
        
        model.addAttribute("prodStatis", this.statisticService.productStatis(null, null, null));
        return "product-statistic";
    }
    
    @GetMapping("/product-month-statistic")
    public String productMonthStatis(Model model, @RequestParam(required = false) Map<String, String> params){
        
        SimpleDateFormat f = new SimpleDateFormat("yyy-MM-dd");
        
        String keyWord = params.getOrDefault("kw", null);
        
        Date fromDate = null, toDate = null;
        
        try{
            String fDate = params.getOrDefault("fromDate", null);
            if (fDate != null) {
                fromDate = f.parse(fDate);
            }
            String tDate = params.getOrDefault("toDate", null);
            if (fDate != null) {
                toDate = f.parse(tDate);
            }
        }catch(ParseException ex){
            ex.printStackTrace();
        }
        
        model.addAttribute("prodMonthStatistic", this.statisticService.productMonthStatis(keyWord, fromDate, toDate));
        return "product-month-statistic";
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ntht.services;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Acer
 */
public interface StatisticService {
    List<Object[]> cateStatis();
    List<Object[]> productStatis(String keyWord, Date fromDate, Date toDate);
    List<Object[]> productMonthStatis(String keyWord, Date fromDate, Date toDate);
}

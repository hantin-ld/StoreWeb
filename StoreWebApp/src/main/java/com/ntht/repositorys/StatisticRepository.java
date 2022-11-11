/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ntht.repositorys;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Acer
 */
public interface StatisticRepository {
    List<Object[]> cateStatis();
    List<Object[]> productStatis(String keyWordString, Date fromDate, Date toDate);
    List<Object[]> productMonthStatis(String keyWordString, Date fromDate, Date toDate);
}

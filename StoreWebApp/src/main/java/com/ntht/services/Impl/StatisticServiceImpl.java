/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntht.services.Impl;

import com.ntht.repositorys.StatisticRepository;
import com.ntht.services.StatisticService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Acer
 */
@Service
public class StatisticServiceImpl implements StatisticService{

    @Autowired
    private StatisticRepository statisticRepository;
    
    @Override
    public List<Object[]> cateStatis() {
        return this.statisticRepository.cateStatis();
    }

    @Override
    public List<Object[]> productStatis(String keyWord, Date fromDate, Date toDate) {
        return this.statisticRepository.productStatis(keyWord, fromDate, toDate);
    }

    @Override
    public List<Object[]> productMonthStatis(String string, Date date, Date date1) {
        return this.statisticRepository.productMonthStatis(string, date, date1);
    }
    
}

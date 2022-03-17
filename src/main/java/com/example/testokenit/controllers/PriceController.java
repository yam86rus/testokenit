package com.example.testokenit.controllers;

import com.example.testokenit.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PriceController {
    @Autowired
    private PriceService priceService;

    // Как часто менялась цена товара, группировка по товарам
    @GetMapping(value = "/products/statistic/changesByName")
    public List<Object[]> getAllChangingByPrice(){
        List<Object[]> list = priceService.findAllPriceChangingByName();
        return list;
    }

    // Как часто менялась цена товара, группировка по дням
    @GetMapping(value = "/products/statistic/changesByDate")
    public List<Object[]> getAllChangingByDate(){
        List<Object[]> list = priceService.findAllPriceChangingByDate();
        return list;
    }

}

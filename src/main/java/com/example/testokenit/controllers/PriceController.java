package com.example.testokenit.controllers;

import com.example.testokenit.json.ProductAndCountByDate;
import com.example.testokenit.json.ProductAndCountByName;
import com.example.testokenit.service.PriceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class PriceController {
    @Autowired
    private PriceService priceService;

    // Как часто менялась цена товара, группировка по товарам
    @GetMapping(value = "/products/statistic/changesByName")
    public String getAllChangingByPrice() {
        List<ProductAndCountByName> list = priceService.findAllPriceChangingByName();
        ObjectMapper objectMapper = new ObjectMapper();
        String result = null;
        try {
            result = objectMapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Как часто менялась цена товара, группировка по дням
    @GetMapping(value = "/products/statistic/changesByDate")
    public String getAllChangingByDate() {
        List<ProductAndCountByDate> list = priceService.findAllPriceChangingByDate();
        ObjectMapper objectMapper = new ObjectMapper();
        String result = null;
        try {
            result = objectMapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

}

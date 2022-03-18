package com.example.testokenit.service;

import com.example.testokenit.json.ProductAndCountByDate;
import com.example.testokenit.json.ProductAndCountByName;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceService {

    List<ProductAndCountByName> findAllPriceChangingByName();
    List<ProductAndCountByDate> findAllPriceChangingByDate();
    void addSomeProducts(String id,double price, LocalDateTime dateTime, String productId);
}

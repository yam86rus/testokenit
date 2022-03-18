package com.example.testokenit.service;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceService {

    List<Object[]> findAllPriceChangingByName();
    List<Object[]> findAllPriceChangingByDate();
    void addSomeProducts(String id,double price, LocalDateTime dateTime, String productId);
}

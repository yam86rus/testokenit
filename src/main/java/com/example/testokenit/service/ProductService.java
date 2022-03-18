package com.example.testokenit.service;

import com.example.testokenit.json.ProductAndPrice;

import java.time.LocalDate;
import java.util.List;

public interface ProductService {
    List<ProductAndPrice> getAllProductsAndPriceByDate(LocalDate localDate);
    long getProductsCount();
    void addSomeProduct(String product, String id);
}

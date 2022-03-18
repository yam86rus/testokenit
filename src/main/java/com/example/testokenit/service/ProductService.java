package com.example.testokenit.service;

import java.time.LocalDate;
import java.util.List;

public interface ProductService {
    List<Object[]> getAllProductsAndPriceByDate();
    List<Object[]> getAllProductsAndPriceByDate123(LocalDate localDate);
    long getProductsCount();
    void addSomeProduct(String product, String id);
}

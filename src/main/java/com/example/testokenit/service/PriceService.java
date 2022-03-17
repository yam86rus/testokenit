package com.example.testokenit.service;

import java.util.List;

public interface PriceService {

    List<Object[]> findAllPriceChangingByName();
    List<Object[]> findAllPriceChangingByDate();
}

package com.example.testokenit.service;

import com.example.testokenit.dao.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {

    @Autowired
    private PriceRepository priceRepository;

    @Override
    public List<Object[]> findAllPriceChangingByName() {
        List<Object[]> allChanges = priceRepository.findAllChangesByName();
        return allChanges;
    }

    @Override
    public List<Object[]> findAllPriceChangingByDate() {
        List<Object[]> allChanges = priceRepository.findAllChangesByDate();
        return allChanges;
    }
}

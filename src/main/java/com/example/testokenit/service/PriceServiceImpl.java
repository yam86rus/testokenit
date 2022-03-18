package com.example.testokenit.service;

import com.example.testokenit.dao.PriceRepository;
import com.example.testokenit.json.ProductAndCountByDate;
import com.example.testokenit.json.ProductAndCountByName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PriceServiceImpl extends JsonParseServiceImpl implements PriceService {

    @Autowired
    private PriceRepository priceRepository;

    @Override
    public List<ProductAndCountByName> findAllPriceChangingByName() {
        List<Object[]> allChanges = priceRepository.findAllChangesByName();
        List<ProductAndCountByName> resultList = new ArrayList<>();
        for (Object[] objects: allChanges){
            String name = (String) convertObjectToList(objects).get(0);
            BigInteger countChanges = (BigInteger) convertObjectToList(objects).get(1);
            resultList.add(new ProductAndCountByName(name,countChanges));
        }
        return resultList;
    }

    @Override
    public List<ProductAndCountByDate> findAllPriceChangingByDate() {
        List<Object[]> allChanges = priceRepository.findAllChangesByDate();
        List<ProductAndCountByDate> resultList = new ArrayList<>();
        for (Object[] objects: allChanges) {
            Date date = (Date) convertObjectToList(objects).get(0);
            BigInteger countChanges = (BigInteger) convertObjectToList(objects).get(1);
            resultList.add(new ProductAndCountByDate(date,countChanges));
        }
        return resultList;
    }

    @Override
    public void addSomeProducts(String id, double price, LocalDateTime dateTime, String productId) {
        try {
            priceRepository.addSomePrices(id,price, dateTime, productId);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}

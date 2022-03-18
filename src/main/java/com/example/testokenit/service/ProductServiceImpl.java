package com.example.testokenit.service;

import com.example.testokenit.dao.ProductRepository;
import com.example.testokenit.json.ProductAndPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl extends JsonParseServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<ProductAndPrice> getAllProductsAndPriceByDate(LocalDate localDate) {
        List<Object[]> allProductsAndPrice = productRepository.findAllProductsAndPricesByDate(localDate);
        List<ProductAndPrice> resultList = new ArrayList<>();
        for (Object[] objects : allProductsAndPrice) {
            String name = (String) (convertObjectToList(objects)).get(0);
            BigDecimal price = (BigDecimal) convertObjectToList(objects).get(1);
            resultList.add(new ProductAndPrice(name,price));
        }
        return resultList;
    }

    @Override
    public long getProductsCount() {
        return productRepository.count();
    }

    @Override
    public void addSomeProduct(String product, String id) {
        try {
            productRepository.addSomeProduct(product, id);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

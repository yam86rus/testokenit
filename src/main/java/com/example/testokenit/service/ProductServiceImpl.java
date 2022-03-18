package com.example.testokenit.service;

import com.example.testokenit.dao.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Object[]> getAllProductsAndPriceByDate() {
        return productRepository.findAllProductsAndPricesByDate();
    }

    @Override
    public List<Object[]> getAllProductsAndPriceByDate123(LocalDate localDate) {
        return productRepository.findAllProductsAndPricesByDate123(localDate);
    }

    @Override
    public long getProductsCount() {
        return productRepository.count();
    }

    @Override
    public void addSomeProduct(String product, String id) {
        try {
            productRepository.addSomeProduct(product,id);
        } catch (Exception e ) {
            System.out.println(e);
        }
    }
}

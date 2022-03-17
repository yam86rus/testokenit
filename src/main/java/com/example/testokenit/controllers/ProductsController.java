package com.example.testokenit.controllers;

import com.example.testokenit.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductsController {

    @Autowired
    private ProductService productService;

    // Возвращает количество товаров
    @GetMapping("/products/statistic/products")
    public long getAllProductsCount() {
        return productService.getProductsCount();
    }

//     Возвращает название товара и количество на определенную дату
    @GetMapping("/products/")
    public List<Object[]> allProducts() {
        List<Object[]> getAllProductsAndPrice = productService.getAllProductsAndPriceByDate();
        System.out.println(getAllProductsAndPrice);
        return getAllProductsAndPrice;
    }

    //     Возвращает название товара и количество на определенную дату
    @GetMapping("/products123/")
    public List<Object[]> getAllProducts(@RequestParam(name = "date") LocalDate localDate) {
        List<Object[]> getAllProductsAndPrice = productService.getAllProductsAndPriceByDate123(localDate);
        System.out.println(localDate);

        return getAllProductsAndPrice;
    }



}

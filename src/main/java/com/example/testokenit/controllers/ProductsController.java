package com.example.testokenit.controllers;

import com.example.testokenit.exception_handling.NoSuchProductsOnDataException;
import com.example.testokenit.exception_handling.PriceIncorrectDate;
import com.example.testokenit.json.ProductAndPrice;
import com.example.testokenit.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/api",produces = {MediaType.APPLICATION_JSON_VALUE })
public class ProductsController {

    @Autowired
    private ProductService productService;

    // Возвращает количество товаров
    @GetMapping("/products/statistic/products")
    public long getAllProductsCount() {
        return productService.getProductsCount();
    }


    // Возвращает название товара и количество на определенную дату
    @GetMapping("/products/")
    public String getAllProducts(@RequestParam(name = "date") LocalDate localDate) {
        List<ProductAndPrice> getAllProductsAndPrice = productService.getAllProductsAndPriceByDate(localDate);
        ObjectMapper objectMapper = new ObjectMapper();
        String result = null;
        try {
            result = objectMapper.writeValueAsString(getAllProductsAndPrice);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if (getAllProductsAndPrice.isEmpty()){
            throw new NoSuchProductsOnDataException("На указанную дату нет товаров");
        }

        return result;
    }




}

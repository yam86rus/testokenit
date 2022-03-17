package com.example.testokenit.dao;

import com.example.testokenit.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Integer> {



    // Как часто менялась цена товара. Группировка по товарам.
    @Query(value = "SELECT products.name, COUNT(prices.price) as frequency\n" +
            "    FROM prices\n" +
            "    JOIN products ON products.id = prices.product_id\n" +
            "    GROUP BY products.name;", nativeQuery = true)
    List<Object[]> findAllChangesByName();


    // Как часто менялась цена товара. Группировка по дням.
    @Query(value = "SELECT DATE(prices.datetime) as date, COUNT(*) as frequency\n" +
            "FROM prices\n" +
            "         JOIN products ON products.id = prices.product_id\n" +
            "GROUP BY DATE(prices.datetime)\n" +
            "ORDER BY date DESC;", nativeQuery = true)
    List<Object[]> findAllChangesByDate();



}

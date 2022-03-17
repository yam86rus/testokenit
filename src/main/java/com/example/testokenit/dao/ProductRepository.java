package com.example.testokenit.dao;

import com.example.testokenit.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    // Получение списка товаров и актуальных цен.
    @Query(value = "SELECT products.name,\n" +
            "       prices.price\n" +
            "FROM prices\n" +
            "         JOIN products ON products.id = prices.product_id\n" +
            "WHERE DATE(prices.datetime) = '2022-03-16' \n" +
            "ORDER BY prices.datetime DESC;", nativeQuery = true)
    List<Object[]> findAllProductsAndPricesByDate();

    // ТЕСТ
    @Query(value = "SELECT products.name,\n" +
            "       prices.price\n" +
            "FROM prices\n" +
            "         JOIN products ON products.id = prices.product_id\n" +
            "WHERE DATE(prices.datetime) = :someDate \n" +
            "ORDER BY prices.datetime DESC;", nativeQuery = true)
    List<Object[]> findAllProductsAndPricesByDate123(@Param("someDate")LocalDate localDate);

    long count();
}

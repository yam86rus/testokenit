package com.example.testokenit.dao;

import com.example.testokenit.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    // Добавляем записи из прочтенного файла
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO prices (id,price,datetime,product_id) VALUES (to_number(:id,'999999'),:price,:datetime,to_number(:product_id,'999999'));", nativeQuery = true)
    void addSomePrices(@Param("id") String id, @Param("price") double price, @Param("datetime") LocalDateTime dateTime, @Param("product_id") String productId);


}

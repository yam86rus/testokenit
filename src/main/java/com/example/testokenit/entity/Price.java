package com.example.testokenit.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "prices")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "price")
    private double price;

    @Column(name = "datetime")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate localDate;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Price() {
    }

    public Price(double price, LocalDate localDate, Product product) {
        this.price = price;
        this.localDate = localDate;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Price{" +
                "id=" + id +
                ", price=" + price +
                ", localDate=" + localDate +
                ", product=" + product +
                '}';
    }
}

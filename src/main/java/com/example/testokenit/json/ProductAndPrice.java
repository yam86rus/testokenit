package com.example.testokenit.json;

import java.math.BigDecimal;

public class ProductAndPrice {
    private String product;
    private BigDecimal price;

    public ProductAndPrice(String product, BigDecimal price) {
        this.product = product;
        this.price = price;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

package com.example.testokenit.json;

import java.math.BigInteger;

public class ProductAndCountByName {
    private String name;
    private BigInteger frequency;

    public ProductAndCountByName(String name, BigInteger count) {
        this.name = name;
        this.frequency = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getFrequency() {
        return frequency;
    }

    public void setFrequency(BigInteger frequency) {
        this.frequency = frequency;
    }
}

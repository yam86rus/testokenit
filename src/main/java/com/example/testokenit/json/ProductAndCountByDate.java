package com.example.testokenit.json;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;

import java.math.BigInteger;
import java.util.Date;

public class ProductAndCountByDate {
    @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private BigInteger frequency;

    public ProductAndCountByDate(Date date, BigInteger frequency) {
        this.date = date;
        this.frequency = frequency;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigInteger getFrequency() {
        return frequency;
    }

    public void setFrequency(BigInteger frequency) {
        this.frequency = frequency;
    }
}

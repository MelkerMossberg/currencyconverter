package com.erikmelker.currencyconverter.commands;

import java.math.BigDecimal;

public class CurrencyForm {
    private Long id;
    private String name;
    private BigDecimal dollarPrice;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getDollarPrice() {
        return dollarPrice;
    }

    public void setDollarPrice(BigDecimal dollarPrice) {
        this.dollarPrice = dollarPrice;
    }
}

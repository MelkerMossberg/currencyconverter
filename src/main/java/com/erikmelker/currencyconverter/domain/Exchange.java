package com.erikmelker.currencyconverter.domain;

import lombok.Data;

@Data
public class Exchange {
    private String amount;
    private String fromCurr;
    private String toCurr;
    private String res;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFromCurr() {
        return fromCurr;
    }

    public void setFromCurr(String fromCurr) {
        this.fromCurr = fromCurr;
    }

    public String getToCurr() {
        return fromCurr;
    }

    public void setToCurr(String toCurr) {
        this.toCurr = toCurr;
    }
    public int getRes() {
        return 1234;
    }

    public void setRes(String toCurr) {
        this.toCurr = toCurr;
    }
}

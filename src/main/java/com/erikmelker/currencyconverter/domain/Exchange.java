package com.erikmelker.currencyconverter.domain;

public class Exchange {
    private long amount;
    private long fromCurr;
    private long toCurr;
    private long res;

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getFromCurr() {
        return fromCurr;
    }

    public void setFromCurr(long fromCurr) {
        this.fromCurr = fromCurr;
    }

    public long getToCurr() {
        return fromCurr;
    }

    public void setToCurr(long toCurr) {
        this.toCurr = toCurr;
    }
    public long getRes() {
        return amount*2;
    }

    public void setRes(long toCurr) {
        this.toCurr = toCurr;
    }
}

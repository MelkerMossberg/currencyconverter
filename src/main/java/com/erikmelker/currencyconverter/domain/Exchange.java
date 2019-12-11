package com.erikmelker.currencyconverter.domain;

public class Exchange {
    private long id;
    private long fromCurr;
    private long toCurr;
    private long res;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}

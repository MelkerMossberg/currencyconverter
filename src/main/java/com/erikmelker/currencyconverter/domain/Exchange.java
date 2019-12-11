package com.erikmelker.currencyconverter.domain;

import lombok.Data;
import sun.lwawt.macosx.CSystemTray;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
        return toCurr;
    }

    public void setToCurr(String toCurr) {
        this.toCurr = toCurr;
    }
    public String getRes() {
        return amount;
    }

    public void setRes(String toCurr) {
        this.toCurr = toCurr;
    }

    public void setRes(BigDecimal fromPrice, BigDecimal toPrice) {
        System.out.println("HIT");
        BigDecimal fromDiv = fromPrice.divide(new BigDecimal(amount),4 ,RoundingMode.CEILING);
        BigDecimal toDivDiv = toPrice.divide(new BigDecimal(amount),4 ,RoundingMode.CEILING);

        res = String.valueOf((fromDiv.subtract(toDivDiv)).multiply(fromPrice));
        System.out.println("Res is actually: " + res);
    }
}

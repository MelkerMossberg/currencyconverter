package com.erikmelker.currencyconverter.converters;

import com.erikmelker.currencyconverter.commands.CurrencyForm;
import com.erikmelker.currencyconverter.domain.Currency;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CurrencyToCurrencyForm {

    public CurrencyForm convert(Currency currency){
        CurrencyForm currencyForm = new CurrencyForm();
        currencyForm.setId(currency.getId());
        currencyForm.setName(currency.getName());
        currencyForm.setDollarPrice(currency.getDollarPrice());
        return currencyForm;
    }
}

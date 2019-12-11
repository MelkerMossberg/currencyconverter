package com.erikmelker.currencyconverter.converters;

import com.erikmelker.currencyconverter.commands.CurrencyForm;
import com.erikmelker.currencyconverter.domain.Currency;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

@Component
public class CurrencyFormToCurrency implements Converter<CurrencyForm, Currency> {

    @Override
    public Currency convert(CurrencyForm currencyForm) {
        Currency currency = new Currency();
        if(currencyForm.getId() != null && !StringUtils.isEmpty(String.valueOf(currencyForm.getId()))){
            currency.setId(new Long(currencyForm.getId()));
        }
        currency.setName(currencyForm.getName());
        currency.setDollarPrice(currencyForm.getDollarPrice());
        return currency;
    }
}

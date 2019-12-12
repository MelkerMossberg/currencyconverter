package com.erikmelker.currencyconverter.services;

import com.erikmelker.currencyconverter.commands.CurrencyForm;
import com.erikmelker.currencyconverter.domain.Currency;

import java.util.List;

public interface CurrencyService {
    List<Currency> listaAll();

    Currency getById(Long id);

    Currency saveOrUpdate(Currency currency);

    void delete(Long id);

    Currency saveOrUpdateCurrencyForm(CurrencyForm currencyForm);
}

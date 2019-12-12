package com.erikmelker.currencyconverter.services;

import com.erikmelker.currencyconverter.commands.CurrencyForm;
import com.erikmelker.currencyconverter.converters.CurrencyFormToCurrency;
import com.erikmelker.currencyconverter.domain.Currency;
import com.erikmelker.currencyconverter.repositories.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CurrencyServiceImpl implements CurrencyService{

    private CurrencyRepository currencyRepository;
    private CurrencyFormToCurrency currencyFormToCurrency;

    @Autowired
    public CurrencyServiceImpl(CurrencyRepository currencyRepository, CurrencyFormToCurrency currencyFormToCurrency) {
        this.currencyRepository = currencyRepository;
        this.currencyFormToCurrency = currencyFormToCurrency;
    }

    @Override
    public List<Currency> listaAll() {
        List<Currency> currencies = new ArrayList<>();
        currencyRepository.findAll().forEach(currencies::add);
        return currencies;
    }

    @Override
    public Currency getById(Long id) {
        return currencyRepository.findById(id).orElse(null);
    }

    @Override
    public Currency saveOrUpdate(Currency currency) {
        currencyRepository.save(currency);
        return currency;
    }

    @Override
    public void delete(Long id) {
        currencyRepository.deleteById(id);
    }

    @Override
    public Currency saveOrUpdateCurrencyForm(CurrencyForm currencyForm) {
        Currency savedCurrency = saveOrUpdate(currencyFormToCurrency.convert(currencyForm));
        System.out.println("Saved currency Id: " + savedCurrency.getId());
        return savedCurrency;
    }
}

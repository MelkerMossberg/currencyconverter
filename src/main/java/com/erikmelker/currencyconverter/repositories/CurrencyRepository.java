package com.erikmelker.currencyconverter.repositories;

import com.erikmelker.currencyconverter.domain.Currency;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

public interface CurrencyRepository extends CrudRepository<Currency, Long> {
}
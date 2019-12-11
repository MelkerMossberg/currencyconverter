package com.erikmelker.currencyconverter.repositories;

import com.erikmelker.currencyconverter.domain.Currency;
import org.springframework.data.repository.CrudRepository;

public interface CurrencyRepository extends CrudRepository<Currency, Long> {
}

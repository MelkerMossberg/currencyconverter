package com.erikmelker.currencyconverter.controllers;

import com.erikmelker.currencyconverter.commands.CurrencyForm;
import com.erikmelker.currencyconverter.converters.CurrencyToCurrencyForm;
import com.erikmelker.currencyconverter.domain.Currency;
import com.erikmelker.currencyconverter.domain.Exchange;
import com.erikmelker.currencyconverter.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Controller
public class CurrencyController {
    private CurrencyService currencyService;

    private CurrencyToCurrencyForm currencyToCurrencyForm;

    @Autowired
    public void setCurrencyToCurrencyForm(CurrencyToCurrencyForm currencyToCurrencyForm){
        this.currencyToCurrencyForm = currencyToCurrencyForm;
    }

    @Autowired
    public void setCurrencyService(CurrencyService currencyService){
        this.currencyService = currencyService;
    }

    @RequestMapping("/")
    public String redirToList() {
        return "redirect:/currency/list";
    }

    @RequestMapping({"/currency/list", "/currency"})
    public String listCurrencies(Model model){
        model.addAttribute("currencies", currencyService.listaAll());
        return "currency/list";
    }

    @RequestMapping("/currency/show/{id}")
    public String getCurrency(@PathVariable String id, Model model){
        model.addAttribute("currency", currencyService.getById(Long.valueOf(id)));
        model.addAttribute("currencies", currencyService.listaAll());
        model.addAttribute("exchange", new Exchange());
        model.addAttribute("exchangeResult", null);
        return "currency/show";
    }

    @RequestMapping("/currency/edit/{id}")
    public String edit(@PathVariable String id, Model model){
        Currency currency = currencyService.getById(Long.valueOf(id));
        CurrencyForm currencyForm = currencyToCurrencyForm.convert(currency);

        model.addAttribute("currencyForm", currencyForm);
        return "currency/currencyform";
    }

    @RequestMapping("/currency/new")
    public String newCurrency(Model model){
        model.addAttribute("currencyForm", new CurrencyForm());
        return "currency/currencyform";
    }

    @RequestMapping(value = "/currency", method = RequestMethod.POST)
    public String saveOrUpdateCurrency(@Valid CurrencyForm currencyForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "currency/currencyform";
        }
        Currency savedCurrency = currencyService.saveOrUpdateCurrencyForm(currencyForm);
        return "redirect:/currency/show/" + savedCurrency.getId();
    }

    @RequestMapping("/currency/delete/{id}")
    public String delete(@PathVariable String id){
        currencyService.delete(Long.valueOf(id));
        return "redirect:/currency/list";
    }

    @PostMapping("/currency/show")
    public String exchangeSubmit(@ModelAttribute("exchange") Exchange exchange, Model model){
        System.out.println("Exchange fromCurr: " + exchange.getFromCurr() + ", toCurr: " + exchange.getToCurr() + ", res: " + exchange.getRes());
        Currency fromCurr = currencyService.getById(Long.valueOf(exchange.getFromCurr()));
        Currency toCurr = currencyService.getById(Long.valueOf(exchange.getToCurr()));

        System.out.println("testing in echangeSubmit: "+exchange.getRes());
        model.addAttribute("currency", fromCurr);
        model.addAttribute("currencies", currencyService.listaAll());
        model.addAttribute("exchange", exchange);
        model.addAttribute("exchangeResult", calculateExchange(fromCurr,toCurr, exchange));
        return "currency/show";
    }

    private String calculateExchange(Currency fromCurr, Currency toCurr, Exchange exchange) {
        BigDecimal fromDiv = fromCurr.getDollarPrice().divide(new BigDecimal(exchange.getAmount()),4 , RoundingMode.CEILING);
        BigDecimal toDivDiv = toCurr.getDollarPrice().divide(new BigDecimal(exchange.getAmount()),4 ,RoundingMode.CEILING);
        return String.valueOf((fromDiv.subtract(toDivDiv)).multiply(fromCurr.getDollarPrice()));
    }


}

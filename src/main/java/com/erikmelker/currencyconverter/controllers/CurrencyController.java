package com.erikmelker.currencyconverter.controllers;

import com.erikmelker.currencyconverter.commands.CurrencyForm;
import com.erikmelker.currencyconverter.converters.CurrencyToCurrencyForm;
import com.erikmelker.currencyconverter.domain.Currency;
import com.erikmelker.currencyconverter.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

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

    @RequestMapping(value = "/show", method = RequestMethod.POST)
    public String showExchangeRate(@PathVariable String id, Model model){
        model.addAttribute("currency", currencyService.getById(Long.valueOf(id)));
        model.addAttribute("currencies", currencyService.listaAll());
        model.addAttribute("exchange", 12343);
        System.out.println("POST GREJJER HAR GJORTS");
        return "currency/show";
    }
}

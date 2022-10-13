package org.crypto.converter.currencyconverter;

import org.crypto.converter.currencyconverter.domain.CryptoCurrency;
import org.crypto.converter.currencyconverter.model.CurrencyConverterModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.EnumSet;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("cryptocurrencies", EnumSet.allOf(CryptoCurrency.class));
        model.addAttribute("currencyConverterModel", new CurrencyConverterModel());
        return "home/index";
    }

    @PostMapping("/convertCurrency")
    public String convertCurrency(@ModelAttribute("currencyConverterModel") @Valid CurrencyConverterModel currencyConverterModel, BindingResult bindingResult, Model model) {
        model.addAttribute("cryptocurrencies", EnumSet.allOf(CryptoCurrency.class));
        model.addAttribute("currencyConverterModel", currencyConverterModel);
        if (!bindingResult.hasErrors()) {
            model.addAttribute("currentUnitPrice", "6,99");
        }
        return "home/index";
    }

}

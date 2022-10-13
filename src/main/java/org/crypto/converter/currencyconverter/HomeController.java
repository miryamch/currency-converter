package org.crypto.converter.currencyconverter;

import lombok.RequiredArgsConstructor;
import org.crypto.converter.currencyconverter.domain.CryptoCurrency;
import org.crypto.converter.currencyconverter.model.CurrencyConverterModel;
import org.crypto.converter.currencyconverter.service.CurrencyConverterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.EnumSet;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final CurrencyConverterService service;

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
            String formattedPrice = service.getFormattedPrice(currencyConverterModel.getCryptoCurrency(), currencyConverterModel.getIpAddress());
            model.addAttribute("currentUnitPrice", formattedPrice);
        }
        return "home/index";
    }

}

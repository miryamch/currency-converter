package org.crypto.converter.currencyconverter;

import org.crypto.converter.currencyconverter.domain.CryptoCurrency;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.EnumSet;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("cryptocurrencies", EnumSet.allOf(CryptoCurrency.class));
        return "home/index";
    }

}

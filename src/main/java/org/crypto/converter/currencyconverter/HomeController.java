package org.crypto.converter.currencyconverter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.crypto.converter.currencyconverter.domain.CryptoCurrency;
import org.crypto.converter.currencyconverter.model.CurrencyConverterModel;
import org.crypto.converter.currencyconverter.service.CurrencyConverterService;
import org.crypto.converter.currencyconverter.service.GeolocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.EnumSet;
import java.util.Locale;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final CurrencyConverterService currencyConverterService;
    private final GeolocationService geolocationService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("cryptocurrencies", EnumSet.allOf(CryptoCurrency.class));
        model.addAttribute("currencyConverterModel", new CurrencyConverterModel());
        return "home/index";
    }

    @PostMapping("/convertCurrency")
    public String convertCurrency(@ModelAttribute("currencyConverterModel") @Valid CurrencyConverterModel currencyConverterModel, BindingResult bindingResult, Model model, HttpServletRequest request) {
        model.addAttribute("cryptocurrencies", EnumSet.allOf(CryptoCurrency.class));
        model.addAttribute("currencyConverterModel", currencyConverterModel);
        if (!bindingResult.hasErrors()) {
            Locale locale;
            String ipAddress = currencyConverterModel.getIpAddress();
            CryptoCurrency cryptoCurrency = currencyConverterModel.getCryptoCurrency();
            log.debug("Requested currency {} for IP {}", cryptoCurrency, ipAddress);
            if (ipAddress != null) {
                locale = geolocationService.getLocaleByIp(ipAddress);
            } else {
                locale = request.getLocale();
            }

            String formattedPrice = currencyConverterService.getFormattedPrice(cryptoCurrency, locale);
            model.addAttribute("currentUnitPrice", formattedPrice);
        }
        return "home/index";
    }

}

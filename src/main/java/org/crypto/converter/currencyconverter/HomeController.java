package org.crypto.converter.currencyconverter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.crypto.converter.currencyconverter.model.CryptoCurrencyMapper;
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
import java.util.List;
import java.util.Locale;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final CurrencyConverterService currencyConverterService;
    private final GeolocationService geolocationService;
    private final CryptoCurrencyMapper mapper;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("cryptocurrencies", getAllCryptoCurrencies());
        model.addAttribute("currencyConverterModel", new CurrencyConverterModel());
        return "home/index";
    }

    private List<CurrencyConverterModel.CryptoCurrencyDto> getAllCryptoCurrencies() {
        var allCryptoCurrencies = mapper.mapToDtos(currencyConverterService.getAllCryptoCurrencies());
        log.debug(String.format("Found %d currencies", allCryptoCurrencies.size()));
        return allCryptoCurrencies;
    }

    @PostMapping("/convertCurrency")
    public String convertCurrency(@ModelAttribute("currencyConverterModel") @Valid CurrencyConverterModel currencyConverterModel, BindingResult bindingResult, Model model, HttpServletRequest request) {
        model.addAttribute("cryptocurrencies", getAllCryptoCurrencies());
        model.addAttribute("currencyConverterModel", currencyConverterModel);
        if (!bindingResult.hasErrors()) {
            Locale locale;
            String ipAddress = currencyConverterModel.getIpAddress();
            String cryptoCurrencyCode = currencyConverterModel.getCryptoCurrency().getCurrencyCode();
            log.debug("Requested currency {} for IP {}", cryptoCurrencyCode, ipAddress);
            if (ipAddress != null) {
                locale = geolocationService.getLocaleByIp(ipAddress);
            } else {
                locale = request.getLocale();
            }

            String formattedPrice = currencyConverterService.getFormattedPrice(cryptoCurrencyCode, locale);
            model.addAttribute("currentUnitPrice", formattedPrice);
        }
        return "home/index";
    }

}

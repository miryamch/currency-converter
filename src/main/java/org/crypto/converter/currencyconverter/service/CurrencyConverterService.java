package org.crypto.converter.currencyconverter.service;

import org.crypto.converter.currencyconverter.domain.CryptoCurrency;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Locale;

@Service
public class CurrencyConverterService {

    public String getFormattedPrice(CryptoCurrency cryptoCurrency, @Nullable String ipAddress) {
        BigDecimal price = getCryptoCurrencyPrice(cryptoCurrency);
        Locale locale = getLocale(ipAddress);
        return formatPrice(price, locale);
    }

    private String formatPrice(BigDecimal price, Locale locale) {
        return "6,99";
    }

    private BigDecimal getCryptoCurrencyPrice(CryptoCurrency cryptoCurrency) {

        return BigDecimal.ZERO;
    }

    private Locale getLocale(@Nullable String ipAddress) {

        return null;
    }
}

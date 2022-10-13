package org.crypto.converter.currencyconverter.service;

import org.crypto.converter.currencyconverter.domain.CryptoCurrency;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

@Service
public class CurrencyConverterService {

    public String getFormattedPrice(CryptoCurrency cryptoCurrency, Locale locale) {
        return formatPrice(getCryptoCurrencyPrice(cryptoCurrency), locale);
    }

    String formatPrice(BigDecimal value, Locale locale) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        if (value.compareTo(BigDecimal.ZERO) > 0) {
            numberFormat.setMinimumFractionDigits(value.scale());
        }
        return numberFormat.format(value);
    }

    BigDecimal getCryptoCurrencyPrice(CryptoCurrency cryptoCurrency) {
        return cryptoCurrency.getDecimalValue();
    }

}

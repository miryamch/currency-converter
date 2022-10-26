package org.crypto.converter.currencyconverter.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.crypto.converter.currencyconverter.domain.CryptoCurrency;
import org.crypto.converter.currencyconverter.domain.CryptoCurrencyRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyConverterService {
    private final CryptoCurrencyRepository repository;

    public String getFormattedPrice(String currencyCode, Locale locale) {
        CryptoCurrency cryptoCurrency = repository.getByCurrencyCode(currencyCode);
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

    public List<CryptoCurrency> getAllCryptoCurrencies() {
        return repository.findAll();
    }

}

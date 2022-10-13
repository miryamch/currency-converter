package org.crypto.converter.currencyconverter.model;

import lombok.Data;
import org.crypto.converter.currencyconverter.domain.CryptoCurrency;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class CurrencyConverterModel {

    @NotNull
    private CryptoCurrency cryptoCurrency;

    @Pattern(regexp = "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$", message = "{home.index.ipAddress.invalid}")
    private String ipAddress;
}

package org.crypto.converter.currencyconverter.domain;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public enum CryptoCurrency {
    BTC("Bitcoin", BigDecimal.valueOf(1960681, 2)),
    ETH("Ethereum", BigDecimal.valueOf(129988, 2)),
    USDT("Tether", BigDecimal.valueOf(102, 2)),
    BNB("BNP", BigDecimal.valueOf(269, 8));

    private final String displayName;
    private final BigDecimal decimalValue;

    CryptoCurrency(String s, BigDecimal d) {
        displayName = s;
        decimalValue = d;
    }
}

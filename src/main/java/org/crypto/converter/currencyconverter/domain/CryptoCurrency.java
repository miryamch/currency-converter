package org.crypto.converter.currencyconverter.domain;

public enum CryptoCurrency {
    BTC("Bitcoin"),
    ETH("Ethereum"),
    USDT("Tether"),
    BNB("BNP");

    public final String displayName;

    CryptoCurrency(String s) {
        displayName = s;
    }
}

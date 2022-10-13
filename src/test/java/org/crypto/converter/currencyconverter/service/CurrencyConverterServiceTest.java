package org.crypto.converter.currencyconverter.service;

import org.crypto.converter.currencyconverter.domain.CryptoCurrency;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class CurrencyConverterServiceTest {

    CurrencyConverterService service = new CurrencyConverterService();

    static Stream<Arguments> cryptoCurrencyProvider() {
        return Stream.of(
                Arguments.arguments(CryptoCurrency.BTC, new BigDecimal("19606.81")),
                Arguments.arguments(CryptoCurrency.ETH, new BigDecimal("1299.88")),
                Arguments.arguments(CryptoCurrency.USDT, new BigDecimal("1.02")),
                Arguments.arguments(CryptoCurrency.BNB, new BigDecimal("0.00000269"))
        );
    }

    @ParameterizedTest
    @MethodSource("cryptoCurrencyProvider")
    void testGetPriceForCryptoCurrency(CryptoCurrency cryptoCurrency, BigDecimal expected) {
        var actual = service.getCryptoCurrencyPrice(cryptoCurrency);
        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> formatPriceProvider() {
        return Stream.of(
                Arguments.arguments(BigDecimal.valueOf(123456789, 2), new Locale("en", "US"), "$1,234,567.89"),
                Arguments.arguments(BigDecimal.valueOf(123, 6), new Locale("en", "US"), "$0.000123"),
                Arguments.arguments(BigDecimal.valueOf(123456789, 2), new Locale("de", "DE"), "1.234.567,89 €"),
                Arguments.arguments(BigDecimal.valueOf(123456789, 2), new Locale("fr", "FR"), "1 234 567,89 €")
        );
    }

    @ParameterizedTest
    @MethodSource("formatPriceProvider")
    void testFormatPrice(BigDecimal price, Locale locale, String expected) {
        var actual = service.formatPrice(price, locale);
        assertThat(actual).isEqualTo(expected);
    }
}
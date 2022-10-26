package org.crypto.converter.currencyconverter.service;

import org.crypto.converter.currencyconverter.domain.CryptoCurrencyRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CurrencyConverterServiceTest {

    @Mock
    CryptoCurrencyRepository repository;

    @InjectMocks
    CurrencyConverterService service;

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
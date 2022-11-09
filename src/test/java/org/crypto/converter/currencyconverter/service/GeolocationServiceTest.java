package org.crypto.converter.currencyconverter.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.crypto.converter.currencyconverter.domain.CountryLocale;
import org.crypto.converter.currencyconverter.domain.CountryLocaleRepository;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.Locale;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
class GeolocationServiceTest {

    public static final String DEFAULT_IP_ADDRESS = "123.456.7.89";

    @Mock
    HttpClient mockClient;

    @Mock
    HttpResponse<Object> mockResponse;

    @Mock
    CountryLocaleRepository mockRepository;

    @InjectMocks
    private GeolocationService service;

    @Test
    @SneakyThrows
    void shouldReturnCorrectLocale() {
        when(mockClient.send(any(), any())).thenReturn(mockResponse);
        when(mockResponse.body()).thenReturn(new JSONObject().put("countryCode", "DE").toString());
        when(mockRepository.findByCountryCode("DE")).thenReturn(Optional.of(
                CountryLocale.builder().countryCode("DE").country("Germany").locale("de").build()));
        var actual = service.getLocaleByIp(DEFAULT_IP_ADDRESS);
        var expected = new Locale("de", "DE");
        assertThat(actual).isEqualTo(expected);
    }


    @Test
    @SneakyThrows
    void shouldReturnDefaultLocaleIfException() {
        when(mockClient.send(any(), any())).thenReturn(mockResponse);
        when(mockResponse.body()).thenReturn(new JSONObject().put("countryCode", "invalid_code").toString());
        when(mockRepository.findByCountryCode("invalid_code")).thenThrow(new EntityNotFoundException());
        var actual = service.getLocaleByIp(DEFAULT_IP_ADDRESS);
        var expected = new Locale("en", "US");
        assertThat(actual).isEqualTo(expected);
    }
}
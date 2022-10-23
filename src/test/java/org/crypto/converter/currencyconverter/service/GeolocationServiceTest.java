package org.crypto.converter.currencyconverter.service;

import lombok.SneakyThrows;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GeolocationServiceTest {

    public static final String DEFAULT_IP_ADDRESS = "123.456.7.89";

    @Mock
    HttpClient mockClient;

    @Mock
    HttpResponse mockResponse;

    @InjectMocks
    private GeolocationService service;

    @Test
    @SneakyThrows
    void testGetLocaleByIp() {
        when(mockClient.send(any(), any())).thenReturn(mockResponse);
        when(mockResponse.body()).thenReturn(new JSONObject().put("countryCode", "FR").toString());
        var actual = service.getLocaleByIp(DEFAULT_IP_ADDRESS);
        var expected = new Locale("fr", "FR");
        assertThat(actual).isEqualTo(expected);

    }

}
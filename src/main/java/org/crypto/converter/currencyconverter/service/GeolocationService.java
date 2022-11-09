package org.crypto.converter.currencyconverter.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.crypto.converter.currencyconverter.domain.CountryLocale;
import org.crypto.converter.currencyconverter.domain.CountryLocaleRepository;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Locale;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.SECONDS;


@Service
@Slf4j
@RequiredArgsConstructor
public class GeolocationService {

    public static final Locale DEFAULT_LOCALE = new Locale("en", "US");
    public static final String GEOLOCATION_API_URL = "http://ip-api.com/json/";

    HttpClient httpClient = HttpClient.newHttpClient();

    private final CountryLocaleRepository repository;

    public Locale getLocaleByIp(String ipAddress) {
        try {
            log.debug("Trying to resolve locale for IP {}", ipAddress);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(GEOLOCATION_API_URL + ipAddress))
                    .timeout(Duration.of(10, SECONDS))
                    .GET()
                    .build();
            var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject jsonResponse = new JSONObject(response.body());
            log.debug("Received response: {}", jsonResponse);
            String countryCode = jsonResponse.getString("countryCode");
            Optional<CountryLocale> localeFromIp = repository.findByCountryCode(countryCode);
            if (localeFromIp.isEmpty()) {
                throw new EntityNotFoundException("Could not find a matching locale for country code " + countryCode);
            }
            return new Locale(localeFromIp.get().getLocale(), countryCode);
        } catch (Exception e) {
            log.warn("failed to reach geolocation API. Reverting to default locale");
            e.printStackTrace();
            return DEFAULT_LOCALE;
        }
    }
}

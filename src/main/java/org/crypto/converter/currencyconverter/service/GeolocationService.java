package org.crypto.converter.currencyconverter.service;

import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class GeolocationService {
    public Locale getLocaleByIp(String ipAddress) {
        return new Locale("en", "US");
    }
}

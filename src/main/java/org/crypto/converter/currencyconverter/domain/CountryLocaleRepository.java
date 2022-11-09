package org.crypto.converter.currencyconverter.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryLocaleRepository extends JpaRepository<CountryLocale, Long> {
    Optional<CountryLocale> findByCountryCode(String countryCode);
}

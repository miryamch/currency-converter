package org.crypto.converter.currencyconverter.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CryptoCurrencyRepository extends JpaRepository<CryptoCurrency, Long> {
    CryptoCurrency getByCurrencyCode(String currencyCode);
}

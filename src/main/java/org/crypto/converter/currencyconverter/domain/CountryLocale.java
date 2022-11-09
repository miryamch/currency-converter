package org.crypto.converter.currencyconverter.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "countrylocale")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CountryLocale {
    @Id
    @Column(name = "country_code")
    private String countryCode;

    private String country;

    private String locale;
}

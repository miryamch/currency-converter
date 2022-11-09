package org.crypto.converter.currencyconverter.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "countrylocale")
public class CountryLocale {
    @Id
    @Column(name = "country_code")
    private String countryCode;

    private String country;

    private String locale;

}

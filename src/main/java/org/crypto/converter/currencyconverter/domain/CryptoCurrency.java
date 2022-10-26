package org.crypto.converter.currencyconverter.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cryptocurrency")
@NoArgsConstructor
@Getter
public class CryptoCurrency {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "currency_code")
    private String currencyCode;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "unscaled_value")
    private Integer unscaledValue;

    private Integer scale;

    public CryptoCurrency(String currencyCode, String displayName, Integer unscaledValue, Integer scale) {
        this.currencyCode = currencyCode;
        this.displayName = displayName;
        this.unscaledValue = unscaledValue;
        this.scale = scale;
    }

    public BigDecimal getDecimalValue() {
        return BigDecimal.valueOf(unscaledValue, scale);
    }
}

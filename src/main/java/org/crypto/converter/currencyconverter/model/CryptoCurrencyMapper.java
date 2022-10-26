package org.crypto.converter.currencyconverter.model;

import org.crypto.converter.currencyconverter.domain.CryptoCurrency;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CryptoCurrencyMapper {

    CurrencyConverterModel.CryptoCurrencyDto mapToDto(CryptoCurrency cryptoCurrency);

    List<CurrencyConverterModel.CryptoCurrencyDto> mapToDtos(List<CryptoCurrency> cryptoCurrencies);
}

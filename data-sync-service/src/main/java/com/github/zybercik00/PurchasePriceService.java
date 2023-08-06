package com.github.zybercik00;

import com.github.zybercik00.entity.process.Currency;
import com.github.zybercik00.entity.process.Extraction;
import com.github.zybercik00.entity.process.PurchasePrice;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class PurchasePriceService {

    private final CurrencyService currencyService;

    // TODO Lookup by extraction and currency
    public PurchasePrice getPurchasePrice(Extraction extraction, String currencyCode, BigDecimal price) {
        PurchasePrice purchasePrice = new PurchasePrice();
        purchasePrice.setExtraction(extraction);
        Currency currency = getCurrency(currencyCode);
        purchasePrice.setCurrency(currency);
        purchasePrice.setPurchasePrice(price);
        return purchasePrice;
    }

    private Currency getCurrency(String currencyCode) {
        return currencyService.getByCode(currencyCode);
    }

}
package com.github.zybercik00;

import com.github.zybercik00.domain.proces.Extraction;
import com.github.zybercik00.domain.proces.Margin;
import com.github.zybercik00.domain.proces.SalePrice;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class SalePriceService {

    private final MarginService marginService;

    // TODO Lookup by extraction and margin
    public SalePrice getSalePrice(Extraction extraction, String marginName, BigDecimal price) {
        SalePrice salePrice = new SalePrice();
        Margin margin = getMargin(marginName);
        salePrice.setMargin(margin);
        salePrice.setSalePrice(price);
        salePrice.setExtraction(extraction);
        return salePrice;
    }

    private Margin getMargin(String name) {
        return marginService.getByName(name);
    }

}
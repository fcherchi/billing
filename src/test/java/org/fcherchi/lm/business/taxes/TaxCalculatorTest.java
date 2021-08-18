package org.fcherchi.lm.business.taxes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TaxCalculatorTest {

    @Mock
    private TaxConfiguration taxConfiguration;

    @Test
    void calculateSalesTax() {
        TaxCalculator taxCalculator = new TaxCalculator(taxConfiguration);
        Mockito.when(taxConfiguration.getSalesTax()).thenReturn(10.0);
        double cdPrice = 14.99;
        double finalPrice = taxCalculator.getPricePlusSalesTax(cdPrice);
        Assertions.assertEquals(16.49, finalPrice, "Expected 16.49 as per exercise example.");
    }

    @Test
    void calculateOnlyImportTax() {
        TaxCalculator taxCalculator = new TaxCalculator(taxConfiguration);
        Mockito.when(taxConfiguration.getImportTax()).thenReturn(5.0);
        double rawPrice = 10.00;
        double finalPrice = taxCalculator.getPricePlusImportTax(rawPrice);
        Assertions.assertEquals(10.50, finalPrice, "Expected 10.50 as per exercise example.");
    }

}
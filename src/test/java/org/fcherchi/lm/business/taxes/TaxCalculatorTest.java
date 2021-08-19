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
    void calculateOnlySalesTax() {
        TaxCalculator taxCalculator = new TaxCalculator(taxConfiguration);
        Mockito.when(taxConfiguration.getSalesTax()).thenReturn(10.0);
        Assertions.assertEquals(16.49, taxCalculator.getPricePlusSalesTax(14.99), "Wrong result as per exercise example.");
        Assertions.assertEquals(20.89, taxCalculator.getPricePlusSalesTax(18.99), "Wrong result as per exercise example.");
    }

    @Test
    void calculateOnlyImportTax() {
        TaxCalculator taxCalculator = new TaxCalculator(taxConfiguration);
        Mockito.when(taxConfiguration.getImportTax()).thenReturn(5.0);
        Assertions.assertEquals(10.50, taxCalculator.getPricePlusImportTax(10.00), "Wrong result as per exercise example.");
        Assertions.assertEquals(11.85, taxCalculator.getPricePlusImportTax(11.25), "Wrong result as per exercise example.");
    }

    @Test
    void calculateImportAndSalesTax() {
        TaxCalculator taxCalculator = new TaxCalculator(taxConfiguration);
        Mockito.when(taxConfiguration.getImportTax()).thenReturn(5.0);
        Mockito.when(taxConfiguration.getSalesTax()).thenReturn(10.0);
        Assertions.assertEquals(54.65, taxCalculator.getPricePlusSalesAndImportTax(47.50), "EWrong result as per exercise example.");
        Assertions.assertEquals(32.19, taxCalculator.getPricePlusSalesAndImportTax(27.99), "EWrong result as per exercise example.");
    }
}
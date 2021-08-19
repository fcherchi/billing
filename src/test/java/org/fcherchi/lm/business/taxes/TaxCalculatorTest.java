package org.fcherchi.lm.business.taxes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TaxCalculatorTest {

    private static final double SALES_TAX = 10.00;
    private static final double IMPORT_TAX = 5.00;
    private static final double NO_TAX = 0.00;

    @Test
    void calculateOnlySalesTax() {
        TaxCalculator taxCalculator = new TaxCalculator();
        Assertions.assertEquals(16.49, taxCalculator.getPricePlusTaxes(14.99, SALES_TAX, NO_TAX, 1.0), "Wrong result as per exercise example.");
        Assertions.assertEquals(20.89, taxCalculator.getPricePlusTaxes(18.99, SALES_TAX, NO_TAX, 1.0), "Wrong result as per exercise example.");
    }

    @Test
    void calculateOnlyImportTax() {
        TaxCalculator taxCalculator = new TaxCalculator();
        Assertions.assertEquals(10.50, taxCalculator.getPricePlusTaxes(10.00, NO_TAX, IMPORT_TAX, 1.0), "Wrong result as per exercise example.");
        Assertions.assertEquals(11.85, taxCalculator.getPricePlusTaxes(11.25, NO_TAX, IMPORT_TAX, 1.0), "Wrong result as per exercise example.");
    }

    @Test
    void calculateImportAndSalesTax() {
        TaxCalculator taxCalculator = new TaxCalculator();
        Assertions.assertEquals(54.65, taxCalculator.getPricePlusTaxes(47.50, SALES_TAX, IMPORT_TAX, 1.0), "Wrong result as per exercise example.");
        Assertions.assertEquals(32.19, taxCalculator.getPricePlusTaxes(27.99, SALES_TAX, IMPORT_TAX, 1.0), "Wrong result as per exercise example.");
    }

    @Test
    void calculateImportAndSalesTaxSeveralUnits() {
        TaxCalculator taxCalculator = new TaxCalculator();
        Assertions.assertEquals(109.3, taxCalculator.getPricePlusTaxes(47.50, SALES_TAX, IMPORT_TAX, 2.0), "Wrong result as per exercise example.");

    }
}
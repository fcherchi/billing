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
        double salesTax1 = taxCalculator.getTaxFor(14.99, SALES_TAX, 1.0);
        double salesTax2 = taxCalculator.getTaxFor(18.99, SALES_TAX, 1.0);
        Assertions.assertEquals(16.49, taxCalculator.getPricePlusTaxes(14.99, salesTax1, NO_TAX, 1.0), "Wrong result as per exercise example.");
        Assertions.assertEquals(20.89, taxCalculator.getPricePlusTaxes(18.99, salesTax2, NO_TAX, 1.0), "Wrong result as per exercise example.");
    }

    @Test
    void calculateOnlyImportTax() {
        TaxCalculator taxCalculator = new TaxCalculator();
        double importTax1 = taxCalculator.getTaxFor(10.00, IMPORT_TAX, 1.0);
        double importTax2 = taxCalculator.getTaxFor(11.25, IMPORT_TAX, 1.0);
        Assertions.assertEquals(10.50, taxCalculator.getPricePlusTaxes(10.00, NO_TAX, importTax1, 1.0), "Wrong result as per exercise example.");
        Assertions.assertEquals(11.85, taxCalculator.getPricePlusTaxes(11.25, NO_TAX, importTax2, 1.0), "Wrong result as per exercise example.");
    }

    @Test
    void calculateImportAndSalesTax() {
        TaxCalculator taxCalculator = new TaxCalculator();
        double salesTax1 = taxCalculator.getTaxFor(47.50, SALES_TAX, 1.0);
        double salesTax2 = taxCalculator.getTaxFor(27.99, SALES_TAX, 1.0);
        double importTax1 = taxCalculator.getTaxFor(47.50, IMPORT_TAX, 1.0);
        double importTax2 = taxCalculator.getTaxFor(27.99, IMPORT_TAX, 1.0);

        Assertions.assertEquals(54.65, taxCalculator.getPricePlusTaxes(47.50, salesTax1, importTax1, 1.0), "Wrong result as per exercise example.");
        Assertions.assertEquals(32.19, taxCalculator.getPricePlusTaxes(27.99, salesTax2, importTax2, 1.0), "Wrong result as per exercise example.");
    }

    @Test
    void calculateImportAndSalesTaxSeveralUnits() {
        TaxCalculator taxCalculator = new TaxCalculator();
        double salesTax1 = taxCalculator.getTaxFor(47.50, SALES_TAX, 2.0);
        double importTax1 = taxCalculator.getTaxFor(47.50, IMPORT_TAX, 2.0);

        Assertions.assertEquals(109.3, taxCalculator.getPricePlusTaxes(47.50, salesTax1, importTax1, 2.0), "Wrong result as per exercise example.");

    }
}
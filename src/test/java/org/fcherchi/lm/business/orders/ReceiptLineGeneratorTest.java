package org.fcherchi.lm.business.orders;

import org.fcherchi.lm.business.taxes.ConfigurationProvider;
import org.fcherchi.lm.business.taxes.TaxConfiguration;
import org.fcherchi.lm.entities.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

/**
 * This test class uses mocks for testing just the unit.
 */
public class ReceiptLineGeneratorTest {

    private ReceiptLineGenerator receiptLineGenerator;
    private TaxConfiguration mockConfiguration;
    private ConfigurationProvider mockConfigProvider;

    @BeforeEach
    void setup(){

        this.mockConfigProvider = Mockito.mock(ConfigurationProvider.class);
        this.mockConfiguration = Mockito.mock(TaxConfiguration.class);
        Mockito.when(mockConfiguration.getImportTax()).thenReturn(5.0);
        Mockito.when(mockConfiguration.getSalesTax()).thenReturn(10.0);
        Mockito.when(mockConfigProvider.getConfiguration()).thenReturn(mockConfiguration);

        this.receiptLineGenerator = new ReceiptLineGenerator(mockConfigProvider);
    }

    @Test
    void buildReceiptLineNoTaxes() {
        //is imported false
        Product book = getProductWithPrice(12.49, false);
        Optional<TaxException> bookTaxException = Optional.of(TaxException.buildWithSalesTax(1, 0.0));
        Mockito.when(this.mockConfiguration.getTaxExceptionByProductCategoryId(1)).thenReturn(bookTaxException);
        ReceiptLine actual = receiptLineGenerator.buildReceiptLine(new BasketLine(book, 1.0));
        Assertions.assertEquals(12.49, actual.getPriceWithTaxes(), "Price for a not imported book should be the same.");
    }

    @Test
    void testImportFlagWinsOverImportConfig() {
        //is imported false
        Product book = getProductWithPrice(12.49, false);

        //create exception including sales tag
        Optional<TaxException> bookTaxException = Optional.of(TaxException.buildWithSalesAndImportTax(1, 0.0, 5.0));

        Mockito.when(this.mockConfiguration.getTaxExceptionByProductCategoryId(1)).thenReturn(bookTaxException);
        ReceiptLine actual = receiptLineGenerator.buildReceiptLine(new BasketLine(book, 1.0));
        Assertions.assertEquals(12.49, actual.getPriceWithTaxes(), "Price for a not imported book should be the same.");
    }

    @Test
    void testExceptionCanCombineImportAndSalesReduction() {
        //is imported true
        Product product = getProductWithPrice(12.49, true);


        //create exception including sales tag
        Optional<TaxException> prodException = Optional.of(TaxException.buildWithSalesAndImportTax(1, 10.0, 10.0));

        Mockito.when(this.mockConfiguration.getTaxExceptionByProductCategoryId(1)).thenReturn(prodException);
        ReceiptLine actual = receiptLineGenerator.buildReceiptLine(new BasketLine(product, 1.0));
        Assertions.assertEquals(14.99, actual.getPriceWithTaxes(), "Price for product should be 14.99 (12.49 + 1.25 + 1.25).");
    }

    private Product getProductWithPrice(double price, boolean isImported) {
        ProductCategory category = new ProductCategory(1, "Foos", isImported);
        return new Product(1, "Foo", category, price);
    }

    @Test
    void buildReceiptLineImportTaxes() {
        Product book = getProductWithPrice(12.49, true);
        ReceiptLine actual = receiptLineGenerator.buildReceiptLine(new BasketLine(book, 1.0));
        Assertions.assertNotEquals(12.49, actual.getPriceWithTaxes(), "Price for a imported book should apply a tax rate.");
    }

    @Test
    void buildReceiptLineImportAndSalesTaxes() {
        Product importedPerfume = getProductWithPrice(47.50, true);
        ReceiptLine actual = receiptLineGenerator.buildReceiptLine(new BasketLine(importedPerfume, 1.0));
        Assertions.assertEquals(54.65, actual.getPriceWithTaxes(), "Price for a imported perfume should apply sales and import tax rate.");
    }
}
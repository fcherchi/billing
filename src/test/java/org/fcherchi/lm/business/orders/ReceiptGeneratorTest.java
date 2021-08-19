package org.fcherchi.lm.business.orders;

import org.fcherchi.lm.business.taxes.ConfigurationProvider;
import org.fcherchi.lm.business.taxes.TaxConfiguration;
import org.fcherchi.lm.data.entities.BasketLine;
import org.fcherchi.lm.data.entities.Product;
import org.fcherchi.lm.data.entities.ProductCategory;
import org.fcherchi.lm.data.entities.ReceiptLine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ReceiptGeneratorTest {

    @Mock
    private TaxConfiguration taxConfiguration;

    @Mock
    private ConfigurationProvider configurationProvider;

    @InjectMocks
    private ReceiptGenerator receiptGenerator;

    @BeforeEach
    void setup(){

    }

    @Test
    void buildReceiptLineNoTaxes() {
        ProductCategory books = new ProductCategory(1, "Books", false);
        Product book = new Product(1, "Book", books, 12.49);

        BasketLine basketLine = new BasketLine(book, 1.0);
        ReceiptLine actual = receiptGenerator.buildReceiptLine(basketLine);
        Assertions.assertEquals(12.49, actual.getPriceWithTaxes(), "Price for a not imported book should be the same.");
    }

    @Test
    void buildReceiptLineImportTaxes() {
        ProductCategory importedBooks = new ProductCategory(1, "Imported Books", true);
        Product book = new Product(1, "Imported Book", importedBooks, 12.49);
        BasketLine basketLine = new BasketLine(book, 1.0);

        Mockito.when(this.taxConfiguration.getImportTax()).thenReturn(5.0);
        Mockito.when(this.configurationProvider.getConfiguration()).thenReturn(this.taxConfiguration);

        ReceiptLine actual = receiptGenerator.buildReceiptLine(basketLine);
        Assertions.assertNotEquals(12.49, actual.getPriceWithTaxes(), "Price for a imported book should apply a tax rate.");
    }

  //  @Test wip
    void buildReceiptLineImportAndSalesTaxes() {
        ProductCategory importedPerfume = new ProductCategory(1, "Imported Perfume", true);
        Product product = new Product(1, "Imported Bottle Of Perfume", importedPerfume, 47.50);
        BasketLine basketLine = new BasketLine(product, 1.0);

        Mockito.when(this.taxConfiguration.getImportTax()).thenReturn(5.0);
        Mockito.when(this.taxConfiguration.getSalesTax()).thenReturn(10.0);
        Mockito.when(this.configurationProvider.getConfiguration()).thenReturn(this.taxConfiguration);

        ReceiptLine actual = receiptGenerator.buildReceiptLine(basketLine);
        Assertions.assertEquals(54.65, actual.getPriceWithTaxes(), "Price for a imported perfume should apply sales and import tax rate.");
    }
}
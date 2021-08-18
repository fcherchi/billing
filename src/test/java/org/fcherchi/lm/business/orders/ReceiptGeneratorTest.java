package org.fcherchi.lm.business.orders;

import org.fcherchi.lm.business.taxes.TaxConfiguration;
import org.fcherchi.lm.data.entities.BasketLine;
import org.fcherchi.lm.data.entities.Product;
import org.fcherchi.lm.data.entities.ProductCategory;
import org.fcherchi.lm.data.entities.ReceiptLine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ReceiptGeneratorTest {

    @Mock
    private TaxConfiguration taxConfiguration;

    private ReceiptGenerator receiptGenerator = new ReceiptGenerator(taxConfiguration);

    @Test
    void buildReceiptLineNoTaxes() {

        ProductCategory books = new ProductCategory(1, "Books", false);
        Product book = new Product(1, "Book", books, 12.49);

        BasketLine basketLine = new BasketLine(book, 1.0);
        ReceiptLine actual = receiptGenerator.buildReceiptLine(basketLine);
        Assertions.assertEquals(12.49, actual.getPriceWithTaxes(), "Price for a not imported book should be the same.");
    }

  //  @Test
    void buildReceiptLineImportTaxes() {

        ProductCategory importedBooks = new ProductCategory(1, "Imported Books", true);
        Product book = new Product(1, "Imported Book", importedBooks, 12.49);

        BasketLine basketLine = new BasketLine(book, 1.0);
        ReceiptLine actual = receiptGenerator.buildReceiptLine(basketLine);
        Assertions.assertNotEquals(12.49, actual.getPriceWithTaxes(), "Price for a imported book should apply a tax rate.");
    }
}
package org.fcherchi.lm.business.impl;

import org.fcherchi.lm.business.ReceiptGenerator;
import org.fcherchi.lm.data.entities.BasketLine;
import org.fcherchi.lm.data.entities.Product;
import org.fcherchi.lm.data.entities.ProductCategory;
import org.fcherchi.lm.data.entities.ReceiptLine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ReceiptGeneratorImplTest {

    private ReceiptGenerator receiptGenerator = new ReceiptGeneratorImpl();


    //@Test
    void buildReceiptLineNoTaxes() {

        ProductCategory books = new ProductCategory(1, "Books", 0.0, 0.0);
        Product book = new Product(1, "Book", books, 12.49);

        BasketLine basketLine = new BasketLine(book, 1.0);
        ReceiptLine actual = receiptGenerator.buildReceiptLine(basketLine);
        Assertions.assertEquals(12.49, actual.getPriceWithTaxes(), "Price for a not imported book should be the same.");
    }

    @Test
    void buildReceiptLineImportTaxes() {

        ProductCategory importedBooks = new ProductCategory(1, "Imported Books", 0.0, 0.0);
        Product book = new Product(1, "Imported Book", importedBooks, 12.49);

        BasketLine basketLine = new BasketLine(book, 1.0);
        ReceiptLine actual = receiptGenerator.buildReceiptLine(basketLine);
        Assertions.assertNotEquals(12.49, actual.getPriceWithTaxes(), "Price for a imported book should apply a tax rate.");
    }
}
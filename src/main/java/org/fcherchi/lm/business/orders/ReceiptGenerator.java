package org.fcherchi.lm.business.orders;

import org.fcherchi.lm.business.taxes.TaxCalculator;
import org.fcherchi.lm.business.taxes.TaxConfiguration;
import org.fcherchi.lm.data.entities.BasketLine;
import org.fcherchi.lm.data.entities.ReceiptLine;
/**
 * Generates a Receipt out of a Basket
 */
public class ReceiptGenerator {

    private final TaxCalculator taxCalculator;

    public ReceiptGenerator(TaxConfiguration taxConfiguration) {
        this.taxCalculator = new TaxCalculator(taxConfiguration);
    }

    /**
     * Creates a receipt line with taxes out of a basket line (raw price)
     * @param basketLine
     * @return
     */
    public ReceiptLine buildReceiptLine(BasketLine basketLine) {
        validateBasketLine(basketLine);
        if (basketLine.getProduct().getCategory().getImported()) {
           // double importFee = this.taxCalculator.getImportFeeFor(basketLine.getProduct().getPrice());

        }

        ReceiptLine receiptLine = new ReceiptLine(basketLine, basketLine.getProduct().getPrice());
        return receiptLine;
    }

    /**
     * Defensive method to throw exception if null detected on mandatory fields
     * @param basketLine
     */
    private void validateBasketLine(BasketLine basketLine) {
        if (basketLine.getProduct() == null) throw new IllegalArgumentException("Product in Basket Line cannot be null.");
        if (basketLine.getProduct().getCategory() == null) throw new IllegalArgumentException("ProductCategory in Product in  Basket Line cannot be null.");
    }
}

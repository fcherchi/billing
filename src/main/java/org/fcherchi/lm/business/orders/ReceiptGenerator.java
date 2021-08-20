package org.fcherchi.lm.business.orders;

import org.fcherchi.lm.data.entities.Receipt;
import org.fcherchi.lm.data.entities.ReceiptLine;

import java.math.BigDecimal;

/**
 * Build the receipt information out of a Basket information
 */
public class ReceiptGenerator {

    private final ReceiptLineGenerator receiptLineGenerator;

    /**
     * Creates a Receipt Generator
     * @param receiptLineGenerator the line generator
     */
    public ReceiptGenerator(ReceiptLineGenerator receiptLineGenerator) {
        this.receiptLineGenerator = receiptLineGenerator;
    }

    /**
     * Builds a receipt out of a basket
     * @param basket The basket to be iterated to build a receipt
     * @return the receipt
     */
    public Receipt buildReceipt(Basket basket) {
        Receipt receipt = new Receipt();

        basket.getLines().values()
                .forEach(line -> receipt.addReceiptLine(receiptLineGenerator.buildReceiptLine(line)));

        double totalTaxes = receipt.getReceiptLines().stream()
                .map(line -> addSafe(line.getSalesTax(), line.getImportTax()))
                .reduce(0.0, this::addSafe);

        double total = receipt.getReceiptLines().stream()
                .map(ReceiptLine::getPriceWithTaxes)
                .reduce(0.0, this::addSafe);

        receipt.setSalesTaxes(totalTaxes);
        receipt.setTotal(total);

        return receipt;
    }

    /**
     * Adds using Big Decimal to preserve precision
     * @param a first term to add
     * @param b second term to add
     * @return result
     */
    private double addSafe(double a, double b) {
        return new BigDecimal(Double.toString(a)).add(new BigDecimal(Double.toString(b))).doubleValue();
    }
}

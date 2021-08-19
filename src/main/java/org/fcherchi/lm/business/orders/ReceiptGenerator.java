package org.fcherchi.lm.business.orders;

import org.fcherchi.lm.data.entities.Receipt;

import java.math.BigDecimal;

public class ReceiptGenerator {

    private final ReceiptLineGenerator receiptLineGenerator;

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

        basket.getLines().values().stream()
                .forEach(line -> receipt.addReceiptLine(receiptLineGenerator.buildReceiptLine(line)));

        double totalTaxes = receipt.getReceiptLines().stream()
                .map(line -> addSafe(line.getSalesTax(), line.getImportTax()))
                .reduce(0.0, (a, b) -> addSafe(a, b));

        double total = receipt.getReceiptLines().stream()
                .map(line -> line.getPriceWithTaxes())
                .reduce(0.0, (a, b) -> addSafe(a, b));

        receipt.setSalesTaxes(totalTaxes);
        receipt.setTotal(total);

        return receipt;
    }

    private double addSafe(double a, double b) {
        return new BigDecimal(Double.toString(a)).add(new BigDecimal(Double.toString(b))).doubleValue();
    }
}

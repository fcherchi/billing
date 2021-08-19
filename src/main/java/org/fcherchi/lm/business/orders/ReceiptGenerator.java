package org.fcherchi.lm.business.orders;

import org.fcherchi.lm.data.entities.Receipt;
import org.fcherchi.lm.data.entities.ReceiptLine;

import java.util.List;
import java.util.stream.Collectors;

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
        List<ReceiptLine> receiptLines = basket.getLines().values().stream()
                .map(line -> receiptLineGenerator.buildReceiptLine(line)).collect(Collectors.toList());

        //receiptLines.stream().map(line -> line.)
        return new Receipt();
    }
}

package org.fcherchi.lm.business.orders;

import org.fcherchi.lm.data.entities.BasketLine;
import org.fcherchi.lm.data.entities.ReceiptLine;
/**
 * Generates a Receipt out of a Basket
 */
public class ReceiptGenerator {

    /**
         * Creates a receipt line with taxes out of a basket line (raw price)
         * @param basketLine
         * @return
         */
    public ReceiptLine buildReceiptLine(BasketLine basketLine) {
        ReceiptLine receiptLine = new ReceiptLine(basketLine, basketLine.getProduct().getPrice());
        return receiptLine;
    }
}

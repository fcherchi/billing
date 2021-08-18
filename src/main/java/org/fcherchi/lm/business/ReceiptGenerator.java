package org.fcherchi.lm.business;

import org.fcherchi.lm.data.entities.BasketLine;
import org.fcherchi.lm.data.entities.ReceiptLine;

/**
 * Generates a Receipt out of a Basket
 */
public interface ReceiptGenerator {

    /**
     * Creates a receipt line with taxes out of a basket line (raw price)
     * @param basketLine
     * @return
     */
    ReceiptLine buildReceiptLine(BasketLine basketLine);
}
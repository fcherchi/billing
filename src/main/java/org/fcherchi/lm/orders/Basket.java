package org.fcherchi.lm.orders;

import org.fcherchi.lm.data.entities.BasketLine;

public interface Basket {
    /**
     * Adds the given line to the shopping cart
     * @param basketLine
     */
    void addBasketLine(BasketLine basketLine);

    /**
     * Returns the total number of items currently in the basket
     * @return
     */
    double getTotalItemsInBasket();
}

package org.fcherchi.lm.business;

import org.fcherchi.lm.data.entities.Product;

public interface Basket {
    /**
     * Adds the given items to the shopping cart.
     * @param product The product of the item to add.
     * @param  quantity The quantity. Only >=0 allowed.
     */
    void addItemsToBasket(Product product, Double quantity);

    /**
     * Returns the total number of items currently in the basket.
     * @return
     */
    double getTotalItemsInBasket();

    /**
     * @return The number of different products in the basket.
     */
    int getNumberOfDifferentProductsInBasket();
}

package org.fcherchi.lm.business;

public interface Basket {
    /**
     * Adds the given items to the shopping cart.
     * @param productId The product id of the item to add.
     * @param  quantity The quantity. Only >=0 allowed.
     */
    void addItemsToBasket(Integer productId, Double quantity);

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

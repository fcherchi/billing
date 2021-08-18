package org.fcherchi.lm.business;

public interface OrderManager {
    /**
     * Adds an item to the basket.
     * @param itemId PK of the item.
     * @param quantity Amount of units to be added.
     */
    void addItem(Integer itemId, Double quantity);

    /**
     * Returns the amount of items inside the basket.
     * @return
     */
    double getTotalItemsInBasket();
}

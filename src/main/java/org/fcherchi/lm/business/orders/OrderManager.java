package org.fcherchi.lm.business.orders;

import org.fcherchi.lm.business.exceptions.DataInconsistencyException;
import org.fcherchi.lm.data.DataStorage;
import org.fcherchi.lm.data.entities.Product;

import java.util.Optional;

/**
 * Business class to create orders and manage basket
 */
public class OrderManager {

    /** Stores the data in memory. Kind of data connection */
    private DataStorage dataStorage;

    /** Contains the items while shopping */
    private Basket basket;

    public OrderManager(DataStorage dataStorage, Basket basket) {
        this.dataStorage = dataStorage;
        this.basket = basket;
    }

    /**
         * Adds an item to the basket.
         * @param itemId PK of the item.
         * @param quantity Amount of units to be added.
         */
    public void addItem(Integer itemId, Double quantity) {
        Optional<Product> prod = this.dataStorage.getProductById(itemId);
        if (prod.isEmpty()) {
            throw new DataInconsistencyException(String.format("Product '%d' not found in catalog", itemId));
        }
        this.basket.addItemsToBasket(prod.get(), quantity);
    }

    /**
         * Returns the amount of items inside the basket.
         * @return
         */
    public double getTotalItemsInBasket() {
        return this.basket.getTotalItemsInBasket();
    }
}

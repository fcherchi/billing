package org.fcherchi.lm.business.orders;

import org.fcherchi.lm.business.exceptions.DataInconsistencyException;
import org.fcherchi.lm.data.DataStorage;
import org.fcherchi.lm.entities.Product;

import java.util.Optional;

/**
 * Business class to create orders and manage basket
 */
public class OrderManager {

    /** Stores the data in memory. Kind of data connection */
    private final DataStorage dataStorage;

    /** Contains the items while shopping */
    private final Basket basket;

    /**
     * Creates an order manager with the given dependencies
     * @param dataStorage Represents the connection with the data persistence
     * @param basket The basket or shopping cart
     */
    public OrderManager(DataStorage dataStorage, Basket basket) {
        this.dataStorage = dataStorage;
        this.basket = basket;
    }

    /**
     * Adds an item to the basket.
     * @param itemId PK of the item.
     * @param quantity Amount of units to be added.
     */
    public void addItemToBasket(int itemId, double quantity) {
        Optional<Product> prod = this.dataStorage.getProductById(itemId);
        if (prod.isEmpty()) {
            throw new DataInconsistencyException(String.format("Product '%d' not found in catalog", itemId));
        }
        this.basket.addItemsToBasket(prod.get(), quantity);
    }
}

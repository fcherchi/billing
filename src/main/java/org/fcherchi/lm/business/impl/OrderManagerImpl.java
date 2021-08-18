package org.fcherchi.lm.business.impl;

import org.fcherchi.lm.data.DataStorage;
import org.fcherchi.lm.data.entities.Product;
import org.fcherchi.lm.business.Basket;
import org.fcherchi.lm.business.OrderManager;
import org.fcherchi.lm.business.exceptions.DataInconsistencyException;

import java.util.Optional;

/**
 * Business class to create orders and manage basket
 */
public class OrderManagerImpl implements OrderManager {

    /** Stores the data in memory. Kind of data connection */
    private DataStorage dataStorage;

    /** Contains the items while shopping */
    private Basket basket;

    public OrderManagerImpl(DataStorage dataStorage, Basket basket) {
        this.dataStorage = dataStorage;
        this.basket = basket;
    }

    public void addItem(Integer itemId, Double quantity) {
        Optional<Product> prod = this.dataStorage.getProductById(itemId);
        if (prod.isEmpty()) {
            throw new DataInconsistencyException(String.format("Product '%d' not found in catalog", itemId));
        }
        this.basket.addItemsToBasket(prod.get(), quantity);
    }

    public double getTotalItemsInBasket() {
        return this.basket.getTotalItemsInBasket();
    }
}

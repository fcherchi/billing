package org.fcherchi.lm.orders.impl;

import org.fcherchi.lm.data.DataStorage;
import org.fcherchi.lm.data.entities.Product;
import org.fcherchi.lm.orders.Basket;
import org.fcherchi.lm.orders.OrderManager;
import org.fcherchi.lm.orders.exceptions.DataInconsistencyException;

import java.util.Optional;

/**
 * Business class to create orders and manage basket
 */
public class OrderManagerImpl implements OrderManager {

    private DataStorage dataStorage;

    private Basket basket;

    public OrderManagerImpl(DataStorage dataStorage, Basket basket) {
        this.dataStorage = dataStorage;
        this.basket = basket;
    }

    public void addItem(Integer itemId, Double quantity) {
        Optional<Product> prod = this.dataStorage.getProductById(itemId);
        if (prod.isPresent()) {
            this.basket.addItemsToBasket(prod.get().getId(), quantity);
        } else {
            throw new DataInconsistencyException(String.format("Product '%d' not found in catalog", itemId));
        }
    }

    public double getTotalItemsInBasket() {
        return this.basket.getTotalItemsInBasket();
    }
}

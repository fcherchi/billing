package org.fcherchi.lm.orders.impl;

import org.fcherchi.lm.orders.Basket;
import org.fcherchi.lm.orders.exceptions.DataInconsistencyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BasketImplTest {

    private Basket basket = new BasketImpl();

    @Test
    void addBasketLine() {
        basket.addItemsToBasket(1, 1.0);
        basket.addItemsToBasket(3, 3.0);
        Assertions.assertEquals(4.0, basket.getTotalItemsInBasket());
        Assertions.assertEquals(2, basket.getNumberOfDifferentProductsInBasket());
    }

    @Test
    void noNegativeQuantityAllowed() {
        Assertions.assertThrows(DataInconsistencyException.class, () -> {
            basket.addItemsToBasket(1, -1.0);
        });
    }

    @Test
    void adding2TimesSameProd() {
        basket.addItemsToBasket(1, 1.0);
        basket.addItemsToBasket(1, 3.0);
        Assertions.assertEquals(4.0, basket.getTotalItemsInBasket());
        Assertions.assertEquals(1, basket.getNumberOfDifferentProductsInBasket());
    }
}
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
        Assertions.assertEquals(4.0, basket.getTotalItemsInBasket(), "Amount of prods in basket should match");
        Assertions.assertEquals(2, basket.getNumberOfDifferentProductsInBasket(), "Amount of different products should match");
    }

    @Test
    void noNegativeQuantityAllowed() {
        Assertions.assertThrows(DataInconsistencyException.class, () -> {
            basket.addItemsToBasket(1, -1.0);
        }, "Expected Exception if amount is negative");
    }

    @Test
    void adding2TimesSameProd() {
        basket.addItemsToBasket(1, 1.0);
        basket.addItemsToBasket(1, 3.0);
        Assertions.assertEquals(4.0, basket.getTotalItemsInBasket(), "Expected items of to be added");
        Assertions.assertEquals(1, basket.getNumberOfDifferentProductsInBasket(), "Expected only 1 different product after adding twice the same");
    }

    @Test
    void adding2TimesSameProdWithOtherProdInBetween() {
        basket.addItemsToBasket(1, 1.0);
        basket.addItemsToBasket(2, 5.5);
        basket.addItemsToBasket(1, 3.0);
        Assertions.assertEquals(9.5, basket.getTotalItemsInBasket(), "Expected items to be added");
        Assertions.assertEquals(2, basket.getNumberOfDifferentProductsInBasket(), "Expected only 1 different product after adding twice the same");
    }
}
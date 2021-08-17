package org.fcherchi.lm.orders.impl;

import org.fcherchi.lm.data.entities.BasketLine;
import org.fcherchi.lm.orders.Basket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasketImplTest {

    private Basket basket = new BasketImpl();

    @Test
    void addBasketLine() {
        BasketLine l1 = new BasketLine(1, 1, 1.0);
        BasketLine l2 = new BasketLine(2, 3, 3.0);

        basket.addBasketLine(l1);
        basket.addBasketLine(l2);

        Assertions.assertEquals(4.0, basket.getTotalItemsInBasket());
    }
}
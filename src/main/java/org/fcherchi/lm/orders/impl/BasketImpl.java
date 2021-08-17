package org.fcherchi.lm.orders.impl;

import org.fcherchi.lm.data.entities.BasketLine;
import org.fcherchi.lm.orders.Basket;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents a Basket or Shopping Cart
 */
public class BasketImpl implements Basket {

    /** Contains the lines in the basket */
    private Set<BasketLine> lines = new LinkedHashSet<>();

    @Override
    public void addBasketLine(BasketLine basketLine) {
        this.lines.add(basketLine);
    }

    @Override
    public double getTotalItemsInBasket() {
        return this.lines.stream()
                .map(line -> line.getQuantity())
                .reduce(0.0, (a, b) -> a + b);
    }
}

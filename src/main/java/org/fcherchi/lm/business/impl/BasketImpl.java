package org.fcherchi.lm.business.impl;

import org.fcherchi.lm.data.entities.BasketLine;
import org.fcherchi.lm.business.Basket;
import org.fcherchi.lm.business.exceptions.DataInconsistencyException;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represents a Basket or Shopping Cart
 */
public class BasketImpl implements Basket {

    /** Contains the lines in the basket */
    private Map<Integer, BasketLine> lines = new LinkedHashMap<>();

    @Override
    public void addItemsToBasket(Integer productId, Double quantity) {
        if (quantity < 0.0) {
            throw new DataInconsistencyException(String.format("No negative numbers allowed in quantity. Received '%f'", quantity));
        }
        BasketLine line = lines.getOrDefault(productId, new BasketLine(productId, 0.0));
        line = line.addQuantity(quantity);
        this.lines.put(productId, line);
    }

    @Override
    public double getTotalItemsInBasket() {
        return this.lines.values().stream()
                .map(line -> line.getQuantity())
                .reduce(0.0, (a, b) -> a + b);
    }

    @Override
    public int getNumberOfDifferentProductsInBasket() {
        return this.lines.size();
    }
}

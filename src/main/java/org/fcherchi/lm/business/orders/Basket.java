package org.fcherchi.lm.business.orders;

import org.fcherchi.lm.business.exceptions.DataInconsistencyException;
import org.fcherchi.lm.entities.BasketLine;
import org.fcherchi.lm.entities.Product;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represents a Basket or Shopping Cart
 */
public class Basket {
    /** Contains the lines in the basket */
    private final Map<Integer, BasketLine> lines = new LinkedHashMap<>();

    /**
     * Adds the given items to the shopping cart.
         * @param product The product of the item to add.
         * @param  quantity The quantity. Only >=0 allowed.
         */
    public void addItemsToBasket(Product product, Double quantity) {
        if (quantity < 0.0) {
            throw new DataInconsistencyException(String.format("No negative numbers allowed in quantity. Received '%f'", quantity));
        }
        BasketLine line = lines.getOrDefault(product.getId(), new BasketLine(product, 0.0));
        line = line.addQuantity(quantity);
        this.lines.put(product.getId(), line);
    }

    /**
     *
     * @return the total number of items currently in the basket.
     */
    public double getTotalItemsInBasket() {
        return this.lines.values().stream()
                .map(BasketLine::getQuantity)
                .reduce(0.0, Double::sum);
    }

    /** Get the lines in the Basket */
    public Map<Integer, BasketLine> getLines() {
        return lines;
    }

    /**
     * @return The number of different products in the basket.
         */
    public int getNumberOfDifferentProductsInBasket() {
        return this.lines.size();
    }
}

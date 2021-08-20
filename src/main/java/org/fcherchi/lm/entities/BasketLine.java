package org.fcherchi.lm.entities;

import java.util.Objects;

/**
 * Represents one line of the basket or shopping cart. It is an immutable object.
 */
public class BasketLine {
    /**
     * The link to the product being sold
     */
    private final Product product;
    /**
     * The amount of units being sold
     */
    private final double quantity;

    /**
     * Creates a basket line
     * @param product Product reference
     * @param quantity Quantity of elements being added to basket
     */
    public BasketLine(Product product, double quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    /**
     * Adds the given quantity to the current quantity of the object.
     * @param quantity The quantity to be added.
     * @return The new basket line generated (basket lines are immutable, so a new instance is created)
     */
    public BasketLine addQuantity(double quantity) {
        return new BasketLine(this.product, this.quantity + quantity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasketLine that = (BasketLine) o;
        return Objects.equals(product, that.product) && Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, quantity);
    }

    @Override
    public String toString() {
        return "BasketLine{" +
                "product=" + product +
                ", quantity=" + quantity +
                '}';
    }

    public Product getProduct() {
        return product;
    }

    public double getQuantity() {
        return quantity;
    }
}

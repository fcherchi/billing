package org.fcherchi.lm.data.entities;

import java.util.Objects;

public class BasketLine {


    /**
     * The link to the product being sold
     */
    private Product product;
    /**
     * The amount of units being sold
     */
    private Double quantity;

    public BasketLine(Product product, Double quantity) {
        this.product = product;
        this.quantity = quantity;
    }


    public BasketLine addQuantity(Double quantity) {
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

    public Double getQuantity() {
        return quantity;
    }
}

package org.fcherchi.lm.data.entities;

import java.util.Objects;

public class BasketLine {


    /** The id of the product being sold */
    private Integer productId;
    /** The amount of units being sold */
    private Double quantity;

    @Override
    public String toString() {
        return "BasketLine{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }

    public BasketLine(Integer productId, Double quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public BasketLine addQuantity(Double quantity) {
        return new BasketLine(this.productId, this.quantity + quantity);
    }

    public Integer getProductId() {
        return productId;
    }

    public Double getQuantity() {
        return quantity;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasketLine that = (BasketLine) o;
        return Objects.equals(productId, that.productId) && Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, quantity);
    }
}

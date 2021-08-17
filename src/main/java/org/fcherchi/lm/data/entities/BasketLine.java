package org.fcherchi.lm.data.entities;

import java.util.Objects;

public class BasketLine {

    /** The unique identifier of th eline */
    private Integer id;
    /** The id of the product being sold */
    private Integer productId;
    /** The amount of units being sold */
    private Double quantity;

    public BasketLine(Integer id, Integer productId, Double quantity) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasketLine that = (BasketLine) o;
        return Objects.equals(id, that.id) && Objects.equals(productId, that.productId) && Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productId, quantity);
    }

    public Integer getId() {
        return id;
    }

    public Integer getProductId() {
        return productId;
    }

    public Double getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "BasketLine{" +
                "id=" + id +
                ", goodId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}

package org.fcherchi.lm.data.entities;

import java.util.Objects;

public class ReceiptLine {

    /** Composed, the Basket Line that created this receipt line. */
    private BasketLine basketLine;
    /** The price of the basket line after taxes have been applied. */
    private Double priceWithTaxes;


    public ReceiptLine(BasketLine basketLine, Double priceWithTaxes) {
        this.basketLine = basketLine;
        this.priceWithTaxes = priceWithTaxes;
    }


    public BasketLine getBasketLine() {
        return basketLine;
    }

    public Double getPriceWithTaxes() {
        return priceWithTaxes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceiptLine that = (ReceiptLine) o;
        return Objects.equals(basketLine, that.basketLine) && Objects.equals(priceWithTaxes, that.priceWithTaxes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(basketLine, priceWithTaxes);
    }

    @Override
    public String toString() {
        return "ReceiptLine{" +
                "basketLine=" + basketLine +
                ", priceWithTaxes=" + priceWithTaxes +
                '}';
    }
}

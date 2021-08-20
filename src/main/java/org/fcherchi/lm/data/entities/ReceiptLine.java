package org.fcherchi.lm.data.entities;

import java.util.Objects;

/**
 * Represents a receipt line (contains a basket line and all calculated taxes values)
 */
public class ReceiptLine {

    /** Composed, the Basket Line that created this receipt line. */
    private final BasketLine basketLine;
    /** The price of the basket line after taxes have been applied. */
    private final Double priceWithTaxes;
    /** The calculated sales tax */
    private final Double salesTax;
    /** The calculated import tax */
    private final Double importTax;

    /**
     * Constructs a new Receipt line
     * @param basketLine The original basket line
     * @param priceWithTaxes Price including all taxes and multiplied by quantity
     * @param salesTax The amount of sales tax (in monetary unit)
     * @param importTax The amount of import tax (in monetary unit)
     */
    public ReceiptLine(BasketLine basketLine, Double priceWithTaxes, Double salesTax, Double importTax) {
        this.basketLine = basketLine;
        this.priceWithTaxes = priceWithTaxes;
        this.salesTax = salesTax;
        this.importTax = importTax;
    }


    public BasketLine getBasketLine() {
        return basketLine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceiptLine that = (ReceiptLine) o;
        return basketLine.equals(that.basketLine) && priceWithTaxes.equals(that.priceWithTaxes) && salesTax.equals(that.salesTax) && importTax.equals(that.importTax);
    }

    @Override
    public int hashCode() {
        return Objects.hash(basketLine, priceWithTaxes, salesTax, importTax);
    }

    @Override
    public String toString() {
        return "ReceiptLine{" +
                "basketLine=" + basketLine +
                ", priceWithTaxes=" + priceWithTaxes +
                ", salesTax=" + salesTax +
                ", importTax=" + importTax +
                '}';
    }

    public Double getPriceWithTaxes() {
        return priceWithTaxes;
    }

    public Double getSalesTax() {
        return salesTax;
    }

    public Double getImportTax() {
        return importTax;
    }
}

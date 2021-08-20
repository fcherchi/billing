package org.fcherchi.lm.entities;

import java.util.Objects;

/**
 * Represents a receipt line (contains a basket line and all calculated taxes values)
 */
public class ReceiptLine {

    /** Composed, the Basket Line that created this receipt line. */
    private final BasketLine basketLine;
    /** The price of the basket line after taxes have been applied. */
    private final double priceWithTaxes;
    /** The calculated sales tax */
    private final double salesTax;
    /** The calculated import tax */
    private final double importTax;

    /**
     * Constructs a new Receipt line
     * @param basketLine The original basket line
     * @param priceWithTaxes Price including all taxes and multiplied by quantity
     * @param salesTax The amount of sales tax (in monetary unit)
     * @param importTax The amount of import tax (in monetary unit)
     */
    public ReceiptLine(BasketLine basketLine, double priceWithTaxes, double salesTax, double importTax) {
        this.basketLine = basketLine;
        this.priceWithTaxes = priceWithTaxes;
        this.salesTax = salesTax;
        this.importTax = importTax;
    }


    public BasketLine getBasketLine() {
        return basketLine;
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

    public double getPriceWithTaxes() {
        return priceWithTaxes;
    }

    public double getSalesTax() {
        return salesTax;
    }

    public double getImportTax() {
        return importTax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceiptLine that = (ReceiptLine) o;
        return Double.compare(that.priceWithTaxes, priceWithTaxes) == 0 && Double.compare(that.salesTax, salesTax) == 0 && Double.compare(that.importTax, importTax) == 0 && Objects.equals(basketLine, that.basketLine);
    }
}

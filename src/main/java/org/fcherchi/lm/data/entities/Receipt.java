package org.fcherchi.lm.data.entities;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a full bill with total summarized
 */
public class Receipt {

    public Set<ReceiptLine> getReceiptLines() {
        return receiptLines;
    }

    /** Lines with taxes included */
    private Set<ReceiptLine> receiptLines = new LinkedHashSet<>();

    public double getSalesTaxes() {
        return salesTaxes;
    }

    public void setSalesTaxes(double salesTaxes) {
        this.salesTaxes = salesTaxes;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    /** Calculated Sales Taxes */
    private double salesTaxes;

    /** Calculated total including sales */
    private double total;

    /**
     * Adds the given receipt line to the collection
     * @param receiptLine the receipt line to add
     */
    public void addReceiptLine(ReceiptLine receiptLine) {
        this.receiptLines.add(receiptLine);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receipt receipt = (Receipt) o;
        return Double.compare(receipt.salesTaxes, salesTaxes) == 0 && Double.compare(receipt.total, total) == 0 && receiptLines.equals(receipt.receiptLines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(receiptLines, salesTaxes, total);
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "receiptLines=" + receiptLines +
                ", salesTaxes=" + salesTaxes +
                ", total=" + total +
                '}';
    }

    /**
     * Prints the receipt as in the exercise example
     * @return
     */
    public String prettyPrint() {
        StringBuilder builder = new StringBuilder();
        this.receiptLines.stream().forEach(line -> builder.append(String.format("%.0f %s: %.2f\r\n",
                line.getBasketLine().getQuantity(),
                line.getBasketLine().getProduct().getDescription(),
                line.getPriceWithTaxes())));
        builder.append(String.format("Sales Taxes: %.2f\r\n", this.salesTaxes));
        builder.append(String.format("Total: %.2f\r\n", this.total));
        return builder.toString();
    }
}

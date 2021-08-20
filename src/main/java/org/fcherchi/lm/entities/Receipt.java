package org.fcherchi.lm.entities;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a full bill with total summarized
 */
public class Receipt {

    /** Lines with taxes included */
    private final Set<ReceiptLine> receiptLines = new LinkedHashSet<>();

    /** Calculated Sales Taxes + Import Taxes */
    private double taxes;

    /** Calculated total including sales */
    private double total;

    /**
     * Adds the given receipt line to the collection
     * @param receiptLine the receipt line to add
     */
    public void addReceiptLine(ReceiptLine receiptLine) {
        this.receiptLines.add(receiptLine);
    }

    /**
     * Generates the receipt as in the exercise example
     * @return The string to be printed
     */
    public String prettyPrint() {
        StringBuilder builder = new StringBuilder();
        this.receiptLines.forEach(line -> builder.append(String.format("%.0f %s: %.2f\r\n",
                line.getBasketLine().getQuantity(),
                line.getBasketLine().getProduct().getDescription(),
                line.getPriceWithTaxes())));
        builder.append(String.format("Sales Taxes: %.2f%n", this.taxes));
        builder.append(String.format("Total: %.2f%n", this.total));
        return builder.toString();
    }

    //Getter and Setters

    public double getTaxes() {
        return taxes;
    }

    public void setTaxes(double taxes) {
        this.taxes = taxes;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Set<ReceiptLine> getReceiptLines() {
        return receiptLines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receipt receipt = (Receipt) o;
        return Double.compare(receipt.taxes, taxes) == 0 && Double.compare(receipt.total, total) == 0 && receiptLines.equals(receipt.receiptLines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(receiptLines, taxes, total);
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "receiptLines=" + receiptLines +
                ", taxes=" + taxes +
                ", total=" + total +
                '}';
    }


}

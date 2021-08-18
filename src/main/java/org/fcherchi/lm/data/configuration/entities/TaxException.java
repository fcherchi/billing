package org.fcherchi.lm.data.configuration.entities;

import java.util.Objects;

public class TaxException {
    /**
     * Product category. Should match a category associated to the products
     */
    private int productCategoryId;
    /**
     * Sales tax reduction, or zero when exempt.
     */
    private double salesTax;
    /**
     * Import tax reduction, or zero when exempt.
     */
    private double importTax;

    public TaxException(int productCategoryId, double salesTax, double importTax) {
        this.productCategoryId = productCategoryId;
        this.salesTax = salesTax;
        this.importTax = importTax;
    }

    public int getProductCategoryId() {
        return productCategoryId;
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
        TaxException that = (TaxException) o;
        return productCategoryId == that.productCategoryId && Double.compare(that.salesTax, salesTax) == 0 && Double.compare(that.importTax, importTax) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productCategoryId, salesTax, importTax);
    }

    @Override
    public String toString() {
        return "TaxException{" +
                "productCategoryId=" + productCategoryId +
                ", salesTax=" + salesTax +
                ", importTax=" + importTax +
                '}';
    }
}

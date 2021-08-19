package org.fcherchi.lm.data.entities;

import java.util.Objects;
import java.util.Optional;

public class TaxException {
    /**
     * Product category. Should match a category associated to the products
     */
    private int productCategoryId;
    /**
     * Sales tax reduction, or zero when exempt.
     */
    private Optional<Double> salesTax;
    /**
     * Import tax reduction, or zero when exempt.
     */
    private Optional<Double> importTax;

    private TaxException(int productCategoryId, Optional<Double> salesTax, Optional<Double> importTax) {
        this.productCategoryId = productCategoryId;
        this.salesTax = salesTax;
        this.importTax = importTax;
    }

    public static TaxException buildWithSalesTax(int productCategoryId, double salesTax) {
        return new TaxException(productCategoryId, Optional.of(salesTax), Optional.empty());
    }

    public static TaxException buildWithImportTax(int productCategoryId, double importTax) {
        return new TaxException(productCategoryId, Optional.empty(), Optional.of(importTax));
    }
    public static TaxException buildWithSalesAndImportTax(int productCategoryId, double salesTax, double importTax) {
        return new TaxException(productCategoryId, Optional.of(salesTax), Optional.of(importTax));
    }

    public int getProductCategoryId() {
        return productCategoryId;
    }

    public Optional<Double> getSalesTax() {
        return salesTax;
    }

    public Optional<Double> getImportTax() {
        return importTax;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaxException that = (TaxException) o;
        return productCategoryId == that.productCategoryId && Objects.equals(salesTax, that.salesTax) && Objects.equals(importTax, that.importTax);
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

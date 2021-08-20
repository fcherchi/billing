
package org.fcherchi.lm.data.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

/**
 * Entity that represent a tax that is different from the normal case.
 * For instance, an exempt item will have a TaxException with 0.0 sales tax.
 */
public class TaxException implements Serializable {
    /**
     * Product category. Should match a category associated to the products
     */
    private final int productCategoryId;
    /**
     * Sales tax reduction, or zero when exempt.
     */
    private final Double salesTax;
    /**
     * Import tax reduction, or zero when exempt.
     */
    private final Double importTax;

    /**
     * Creates a Tax Exception (represents a special treatment of a category)
     * @param productCategoryId The category this tag exception is linked to
     * @param salesTax The applicable sales tax for this category
     * @param importTax The applicable import tax for this category
     */
    private TaxException(int productCategoryId, Double salesTax, Double importTax) {
        this.productCategoryId = productCategoryId;
        this.salesTax = salesTax;
        this.importTax = importTax;
    }

    /**
     * Builder to build a TaxException with only Sales Tax configured.
     * @param productCategoryId The category this tax exception is linked to
     * @param salesTax The applicable sales tax for this category.
     * @return The newly created exception.
     */
    public static TaxException buildWithSalesTax(int productCategoryId, double salesTax) {
        return new TaxException(productCategoryId, salesTax, null);
    }

    /**
     * Builder to build a TaxException with only Import Tax configured.
     * @param productCategoryId The category this tax exception is linked to
     * @param importTax The applicable import tax for this category.
     * @return The newly created exception.
     */
    public static TaxException buildWithImportTax(int productCategoryId, double importTax) {
        return new TaxException(productCategoryId, null, importTax);
    }

    /**
     * Builder to build a TaxException with both Sales and Import Tax configured.
     * @param productCategoryId The category this tax exception is linked to
     * @param salesTax The applicable Sales Tax for this category
     * @param importTax The applicable import tax for this category.
     * @return The newly created exception.
     */
    public static TaxException buildWithSalesAndImportTax(int productCategoryId, double salesTax, double importTax) {
        return new TaxException(productCategoryId, salesTax, importTax);
    }

    public int getProductCategoryId() {
        return productCategoryId;
    }

    public Optional<Double> getSalesTax() {
        return Optional.ofNullable(salesTax);
    }

    public Optional<Double> getImportTax() {
        return Optional.ofNullable(importTax);
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

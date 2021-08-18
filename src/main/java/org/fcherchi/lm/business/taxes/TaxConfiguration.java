package org.fcherchi.lm.business.taxes;

import org.fcherchi.lm.data.entities.TaxException;
import org.fcherchi.lm.data.exceptions.BadConfigurationException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TaxConfiguration {

    /**
     * Default Sales Tax
     */
    private double salesTax;

    /**
     * Default Import Tax
     */
    private double importTax;

    /**
     * List of exceptions (including exemptions). ProductCategory Id is the key
     */
    private Map<Integer, TaxException> exceptions;

    /**
     * IoC over the validation for Product Category
     */
    private final ProductCategoryValidator productCategoryValidator;

    public TaxConfiguration(double salesTax, double importTax, ProductCategoryValidator productCategoryValidator) {
        this.salesTax = salesTax;
        this.importTax = importTax;
        this.productCategoryValidator = productCategoryValidator;
    }

    /** Gets the sales taxes */
    public double getSalesTax() {
        return salesTax;
    }

    /** Gets the import taxes*/
    public double getImportTax() {
        return importTax;
    }

    /**
     * Adds a Tax Exception to the configuration. No duplicates are allowed.
     *
     * @param taxException
     */
    public void addTaxException(TaxException taxException) {
        if (this.exceptions == null) {
            this.exceptions = new HashMap<>();
        }
        if (this.exceptions.containsKey(taxException.getProductCategoryId())) {
            throw new BadConfigurationException(String.format("Found duplicate on tax exception with Product Category Id '%d'",
                    taxException.getProductCategoryId()));
        }
        if (! this.productCategoryValidator.isProductCategoryConfigurable(taxException.getProductCategoryId())) {
            throw new BadConfigurationException(String.format("Product Category '%d' does not exist or it is not configurable.",
                    taxException.getProductCategoryId()));
        }
        this.exceptions.put(taxException.getProductCategoryId(), taxException);
    }

    /**
     * Retrieves the TaxException by Product Category or Empty if it is not found.
     * @param categoryId
     * @return
     */
    public Optional<TaxException> getExceptionByProductCategoryId(int categoryId) {
        return Optional.ofNullable(this.exceptions.get(categoryId));
    }
}

package org.fcherchi.lm.business.taxes;

import org.fcherchi.lm.data.entities.TaxException;
import org.fcherchi.lm.data.exceptions.BadConfigurationException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Contains the configuration of the taxation scheme
 */
public class TaxConfiguration {

    /**
     * Default Sales Tax
     */
    private final double salesTax;

    /**
     * Default Import Tax
     */
    private final double importTax;

    /**
     * List of tax exceptions (including exemptions). Product Category Id is the key
     */
    private Map<Integer, TaxException> taxExceptions;

    /**
     * IoC over the validation for Product Category
     */
    private final ProductCategoryValidator productCategoryValidator;

    /**
     * Build the tax confguration.
     * @param salesTax the default sales tax
     * @param importTax the default import tax
     * @param productCategoryValidator is used to validate if a certain category is "configurable" with a different tax
     */
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
     * Adds a Tax Exception to the configuration. No duplicates are allowed. No negative tax rate allowed.
     *
     * @param taxException The taxException to be added
     */
    public void addTaxException(TaxException taxException) {
        if (this.taxExceptions == null) {
            this.taxExceptions = new HashMap<>();
        }
        validateRates(taxException.getSalesTax(), taxException.getImportTax());
        if (this.taxExceptions.containsKey(taxException.getProductCategoryId())) {
            throw new BadConfigurationException(String.format("Found duplicate on tax exception with Product Category Id '%d'",
                    taxException.getProductCategoryId()));
        }
        if (! this.productCategoryValidator.isProductCategoryConfigurable(taxException.getProductCategoryId())) {
            throw new BadConfigurationException(String.format("Product Category '%d' does not exist or it is not configurable.",
                    taxException.getProductCategoryId()));
        }
        this.taxExceptions.put(taxException.getProductCategoryId(), taxException);
    }

    /**
     * Throw exception if rates are negative
     * @param salesTax the sales tax rate
     * @param importTax the import tax rate
     */
    private void validateRates(Optional<Double> salesTax, Optional<Double> importTax) {
        salesTax.ifPresent(this::validateRate);
        importTax.ifPresent(this::validateRate);
    }

    private void validateRate(Double rate) {
        if (rate < 0) {
            throw new BadConfigurationException(String.format("Negative Tax Rate detected in configuration: '%.2f')", rate));
        }
    }

    /**
     * Retrieves the TaxException by Product Category or Empty if it is not found.
     * @param categoryId The category id to look for
     * @return An optional with the category ID provided or an Optional.Empty if it could not be found
     */
    public Optional<TaxException> getTaxExceptionByProductCategoryId(int categoryId) {
        return Optional.ofNullable(this.taxExceptions.get(categoryId));
    }
}

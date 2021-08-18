package org.fcherchi.lm.data.configuration;

import org.fcherchi.lm.data.configuration.entities.TaxException;
import org.fcherchi.lm.data.configuration.exceptions.BadConfigurationException;

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

    public TaxConfiguration(double salesTax, double importTax) {
        this.salesTax = salesTax;
        this.importTax = importTax;
    }

    public double getSalesTax() {
        return salesTax;
    }

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
        this.exceptions.put(taxException.getProductCategoryId(), taxException);
    }

    public Optional<TaxException> getExceptionByProductCategoryId(int categoryId) {
        return Optional.ofNullable(this.exceptions.get(categoryId));
    }
}

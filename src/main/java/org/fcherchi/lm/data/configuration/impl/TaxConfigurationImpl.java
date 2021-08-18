package org.fcherchi.lm.data.configuration.impl;

import org.fcherchi.lm.data.configuration.TaxConfiguration;
import org.fcherchi.lm.data.configuration.entities.TaxException;
import org.fcherchi.lm.data.configuration.exceptions.BadConfigurationException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TaxConfigurationImpl implements TaxConfiguration {

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

    public TaxConfigurationImpl(double salesTax, double importTax) {
        this.salesTax = salesTax;
        this.importTax = importTax;
    }

    @Override
    public double getSalesTax() {
        return salesTax;
    }

    @Override
    public double getImportTax() {
        return importTax;
    }

    @Override
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

    @Override
    public Optional<TaxException> getExceptionByProductCategoryId(int categoryId) {
        return Optional.ofNullable(this.exceptions.get(categoryId));
    }
}

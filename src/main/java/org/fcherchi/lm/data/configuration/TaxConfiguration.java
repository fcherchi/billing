package org.fcherchi.lm.data.configuration;

import org.fcherchi.lm.data.configuration.entities.TaxException;

import java.util.Optional;

public interface TaxConfiguration {

    double getSalesTax();

    double getImportTax();

    /**
     * Adds a Tax Exception to the configuration. No duplicates are allowed.
     *
     * @param taxException
     */
    void addTaxException(TaxException taxException);

    Optional<TaxException> getExceptionByProductCategoryId(int categoryId);
}

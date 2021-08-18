package org.fcherchi.lm.data.configuration.entities;

import org.fcherchi.lm.data.configuration.TaxConfiguration;
import org.fcherchi.lm.data.configuration.impl.TaxConfigurationImpl;
import org.fcherchi.lm.data.configuration.exceptions.BadConfigurationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class TaxConfigurationTest {


    @Test
    void addOrUpdateException() {
        TaxConfiguration taxConfiguration = new TaxConfigurationImpl(10.0, 5.0);
        TaxException taxException = new TaxException(1, 0.0, 0.0);
        taxConfiguration.addTaxException(taxException);
        Optional<TaxException> actual = taxConfiguration.getExceptionByProductCategoryId(1);
        Assertions.assertEquals(taxException, actual.get(), "Expected same tax exception");
    }

    @Test
    void addOrUpdateExceptionThrowsExceptionIfProdCategoryExist() {
        TaxConfiguration taxConfiguration = new TaxConfigurationImpl(10.0, 5.0);
        TaxException taxException = new TaxException(1, 0.0, 0.0);
        TaxException taxException2 = new TaxException(1, 1.0, 0.0);
        taxConfiguration.addTaxException(taxException);
        Assertions.assertThrows(BadConfigurationException.class, () -> taxConfiguration.addTaxException(taxException2),
                "No duplicates should be allowed in configuration of tax exceptions");
    }
}
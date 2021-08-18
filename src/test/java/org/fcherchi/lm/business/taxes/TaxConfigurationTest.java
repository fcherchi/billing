package org.fcherchi.lm.business.taxes;

import org.fcherchi.lm.data.entities.TaxException;
import org.fcherchi.lm.data.exceptions.BadConfigurationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class TaxConfigurationTest {

    @Mock
    private ProductCategoryValidator productCategoryValidator;

    @Test
    void addOrUpdateTaxException() {
        Mockito.when(productCategoryValidator.isProductCategoryConfigurable(Mockito.anyInt())).thenReturn(true);
        TaxConfiguration taxConfiguration = new TaxConfiguration(10.0, 5.0, productCategoryValidator);
        TaxException taxException = TaxException.buildWithSalesTax(1, 0.0);
        taxConfiguration.addTaxException(taxException);
        Optional<TaxException> actual = taxConfiguration.getExceptionByProductCategoryId(1);
        Assertions.assertEquals(taxException, actual.get(), "Expected same tax exception");
    }

    @Test
    void addOrUpdateTaxExceptionFailsIfTaxExceptionForSameProdCategoryExist() {
        Mockito.when(productCategoryValidator.isProductCategoryConfigurable(Mockito.anyInt())).thenReturn(true);
        TaxConfiguration taxConfiguration = new TaxConfiguration(10.0, 5.0, productCategoryValidator);
        TaxException taxException = TaxException.buildWithSalesTax(1, 0.0);
        TaxException taxException2 = TaxException.buildWithSalesTax(1, 1.0);
        taxConfiguration.addTaxException(taxException);
        Assertions.assertThrows(BadConfigurationException.class, () -> taxConfiguration.addTaxException(taxException2),
                "No duplicates are allowed in configuration of tax exceptions");
    }

    @Test
    void addOrUpdateTaxExceptionFailsIfProdCategoryDoesNotExist() {
        Mockito.when(productCategoryValidator.isProductCategoryConfigurable(Mockito.anyInt())).thenReturn(false);
        TaxConfiguration taxConfiguration = new TaxConfiguration(10.0, 5.0, productCategoryValidator);

        Assertions.assertThrows(BadConfigurationException.class, () -> taxConfiguration
                .addTaxException(TaxException.buildWithSalesTax(1, 0.0)),
                "No duplicates should be allowed in configuration of tax exceptions");
    }
}
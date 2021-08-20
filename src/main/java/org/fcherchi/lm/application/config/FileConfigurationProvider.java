package org.fcherchi.lm.application.config;

import org.fcherchi.lm.business.taxes.ConfigurationProvider;
import org.fcherchi.lm.business.taxes.ProductCategoryValidator;
import org.fcherchi.lm.business.taxes.TaxConfiguration;
import org.fcherchi.lm.data.entities.TaxException;
import org.fcherchi.lm.data.exceptions.BadConfigurationException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileConfigurationProvider implements ConfigurationProvider {

    private static final String SALES_TAX_KEY = "salesTax";
    private static final String IMPORT_TAX_KEY = "importTax";
    private static final String EXCEPTIONS_KEY = "exceptions";
    public static final String PRODUCT_CATEGORY_ID_KEY = "id";

    private final Map<String, Object> rawConfiguration;
    private final ProductCategoryValidator productCategoryValidator;
    private TaxConfiguration taxConfiguration;

    public FileConfigurationProvider (Map<String, Object> fileConfiguration, ProductCategoryValidator productCategoryValidator) {
        this.rawConfiguration = fileConfiguration;
        this.productCategoryValidator = productCategoryValidator;
    }

    @Override
    public void initializeConfiguration() {
        Double salesTax = getDoubleValue(SALES_TAX_KEY);
        Double importTax = getDoubleValue(IMPORT_TAX_KEY);
        List<TaxException> taxExceptions = getTaxExceptions();

        this.taxConfiguration = new TaxConfiguration(salesTax, importTax, this.productCategoryValidator);

        taxExceptions.forEach(e -> this.taxConfiguration.addTaxException(e));
    }

    private List<TaxException> getTaxExceptions() {
        Object rawExceptions = this.rawConfiguration.getOrDefault(EXCEPTIONS_KEY, null);
        List<Map<String, Object>> exceptions = null;
        try {
            exceptions = (List<Map<String, Object>>) rawExceptions;
        } catch (ClassCastException e) {
            throw new BadConfigurationException("Cannot parse configuration.", e);
        }
        List<TaxException> taxExceptions = exceptions.stream().map(this::parseException).collect(Collectors.toList());

        return taxExceptions;
    }

    private TaxException parseException(Map<String, Object> taxExceptionMap) {
        TaxException taxException;
        try {
            taxException = buildWithFields(taxExceptionMap);


        } catch (Exception e) {
            throw new BadConfigurationException("Cannot parse Configuration" , e);
        }
        return taxException;
    }

    private TaxException buildWithFields(Map<String, Object> taxExceptionMap) {
        if (taxExceptionMap.containsKey(SALES_TAX_KEY) && !taxExceptionMap.containsKey(IMPORT_TAX_KEY)) {
            return TaxException.buildWithSalesTax((int) taxExceptionMap.get(PRODUCT_CATEGORY_ID_KEY),
                            (double) taxExceptionMap.get(SALES_TAX_KEY));
        }
        if (taxExceptionMap.containsKey(IMPORT_TAX_KEY) && !taxExceptionMap.containsKey(SALES_TAX_KEY)) {
            return TaxException.buildWithImportTax((int) taxExceptionMap.get(PRODUCT_CATEGORY_ID_KEY),
                    (double) taxExceptionMap.get(IMPORT_TAX_KEY));
        }
        return TaxException.
                buildWithSalesAndImportTax((int) taxExceptionMap.get(PRODUCT_CATEGORY_ID_KEY),
                        (double) taxExceptionMap.get(SALES_TAX_KEY),
                        (double) taxExceptionMap.get(IMPORT_TAX_KEY));
    }


    private Double getDoubleValue(String salesTaxKey) {
        if (!this.rawConfiguration.containsKey(salesTaxKey)) {
            throw new BadConfigurationException(String.format("Mandatory field '%s' in configuration not found.", salesTaxKey));
        }
        Object value = this.rawConfiguration.get(salesTaxKey);
        double doubleVal = 0.0;
        try {
            doubleVal = Double.parseDouble(value.toString());
        } catch (NumberFormatException e) {
            throw new BadConfigurationException(String.format("Expected a number but found: '%s", value.toString()), e);
        }

        return doubleVal;
    }

    @Override
    public TaxConfiguration getConfiguration() {
        return this.taxConfiguration;
    }
}

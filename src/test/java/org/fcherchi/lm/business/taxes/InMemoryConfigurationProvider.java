package org.fcherchi.lm.business.taxes;

import org.fcherchi.lm.data.entities.TaxException;

/**
 * Implementation that hard-codes the configuration in memory. To be replaced by a file config.
 */
public class InMemoryConfigurationProvider implements ConfigurationProvider {

    /** Definition of the configuration. This would come from a config file */
    public static final double SALES_TAX = 10.0;
    public static final double IMPORT_TAX = 5.0;

    private static final int BOOKS_CATEGORY_ID = 1;
    private static final int HEALTH_CATEGORY_ID = 3;
    private static final int FOOD_CATEGORY_ID = 4;
    private static final int IMPORTED_FOOD_CATEGORY_ID = 5;

    public static final double NO_TAX = 0.0;

    public static final TaxException BOOKS = TaxException.buildWithSalesTax(BOOKS_CATEGORY_ID, NO_TAX);
    public static final TaxException HEALTH = TaxException.buildWithSalesTax(HEALTH_CATEGORY_ID, NO_TAX);
    public static final TaxException FOOD = TaxException.buildWithSalesTax(FOOD_CATEGORY_ID, NO_TAX);
    public static final TaxException IMPORTED_FOOD = TaxException.buildWithSalesTax(IMPORTED_FOOD_CATEGORY_ID, NO_TAX);

    private final TaxConfiguration taxConfiguration;

    public InMemoryConfigurationProvider(ProductCategoryValidator productCategoryValidator) {
        this.taxConfiguration = new TaxConfiguration(SALES_TAX, IMPORT_TAX, productCategoryValidator);
    }

    @Override
    public void initializeConfiguration() {
        this.taxConfiguration.addTaxException(BOOKS);
        this.taxConfiguration.addTaxException(HEALTH);
        this.taxConfiguration.addTaxException(FOOD);
        this.taxConfiguration.addTaxException(IMPORTED_FOOD);
    }

    @Override
    public TaxConfiguration getConfiguration() {
        return this.taxConfiguration;
    }
}

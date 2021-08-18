package org.fcherchi.lm.factory;

import org.fcherchi.lm.data.entities.Product;
import org.fcherchi.lm.data.entities.ProductCategory;

/**
 * Helps the tests to get new objects.
 */
public class EntitiesFactory {

    public static ProductCategory getCategoryNoTaxes() {
        return new ProductCategory(1, "TestCategory No Tax", false);
    }

    public static Product createProductNoTax(Integer productId){
        return new Product(productId, "AnyProdNoTax", getCategoryNoTaxes(), 2.2);
    }
}

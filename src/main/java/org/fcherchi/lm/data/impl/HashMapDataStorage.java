package org.fcherchi.lm.data.impl;

import org.fcherchi.lm.business.taxes.ProductCategoryValidator;
import org.fcherchi.lm.data.DataStorage;
import org.fcherchi.lm.entities.Product;
import org.fcherchi.lm.entities.ProductCategory;

import java.util.*;

/**
 * Implementation of DataStorage with a HashMap in memory to store the info.
 * Implements ProductCategoryValidator which main purpose is to inform if a category can be configured (if exist in database can be configured)
 */
public class HashMapDataStorage implements DataStorage, ProductCategoryValidator {

    /** The map holding the info */
    private final Map<Integer, Product> products = new HashMap<>();

    private final Map<Integer, ProductCategory> categories = new HashMap<>();

    @Override
    public void addProductToCatalog(Product productToAdd) {
        products.putIfAbsent(productToAdd.getId(), productToAdd);
    }

    @Override
    public Optional<Product> getProductById(Integer key) {
        return Optional.ofNullable(products.get(key));
    }

    @Override
    public int getProductsCount() {
        return this.products.size();
    }

    @Override
    public ProductCategory addOrUpdateProductCategory(int id, String description, Boolean isImported) {
        ProductCategory category = new ProductCategory(id, description, isImported);
        this.categories.put(id, category);
        return category;
    }

    @Override
    public Optional<ProductCategory> getCategoryById(int id) {
        return Optional.ofNullable(categories.get(id));
    }

    @Override
    public boolean isProductCategoryConfigurable(int productCategoryId) {
        //any product category qualify as tax-configurable as long as exist
        return this.categories.containsKey(productCategoryId);
    }
}

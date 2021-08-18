package org.fcherchi.lm.data.impl;

import org.fcherchi.lm.data.DataStorage;
import org.fcherchi.lm.data.entities.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class HashMapDataStorage implements DataStorage {

    /** The map holding the info */
    private Map<Integer, Product> products = new HashMap<>();

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
}

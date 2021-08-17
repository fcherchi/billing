package org.fcherchi.lm.data.impl;

import org.fcherchi.lm.data.DataStorage;
import org.fcherchi.lm.data.entities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class HashMapDataStorageTest {

    private DataStorage dataStorage = new HashMapDataStorage();

    @Test
    void addProductToCatalog() {
        DataStorage dataStorage = new HashMapDataStorage();

        Product product = new Product(1, "P1", 1, 22.12);
        dataStorage.addProductToCatalog(product);
        Assertions.assertEquals(product, dataStorage.getProductById(1).get());
    }

    @Test
    void addProductToCatalogReturnsEmpty() {
        Product product = new Product(1, "P1", 1, 22.12);
        dataStorage.addProductToCatalog(product);
        Assertions.assertEquals(Optional.empty(), dataStorage.getProductById(2));
    }

    @Test
    void productIsImmutable() {
        Product product = new Product(3, "P3", 1, 22.12);
        dataStorage.addProductToCatalog(product);
        Optional<Product> p3 = dataStorage.getProductById(3);
        p3 = Optional.of(new Product(3, "P4", 1, 22.12));
        Optional<Product> p32 = dataStorage.getProductById(3);
        Assertions.assertNotEquals("P4", p32.get().getDescription());
    }
}
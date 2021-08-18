package org.fcherchi.lm.data.impl;

import org.fcherchi.lm.data.DataStorage;
import org.fcherchi.lm.data.entities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class HashMapDataStorageTest {

    private DataStorage dataStorage;

    @BeforeEach
    void setup() {
        dataStorage = new HashMapDataStorage();
    }

    @Test
    void addProductToCatalog() {
        Product product = new Product(1, "P1", 1, 22.12);
        dataStorage.addProductToCatalog(product);
        Assertions.assertEquals(product, dataStorage.getProductById(1).get(), "Expected same product as inserted after retrieval");
    }

    @Test
    void addSameProductTwiceWontDuplicate(){
        Product p1 = new Product(1, "P1", 1, 22.12);
        Product p2 = new Product(1, "P1", 1, 2.12);
        dataStorage.addProductToCatalog(p1);
        dataStorage.addProductToCatalog(p2);
        Assertions.assertEquals(1, dataStorage.getProductsCount(), "No duplicates expected.");
    }

    @Test
    void getProductWithWrongCodeReturnsEmpty() {
        Product product = new Product(1, "P1", 1, 22.12);
        dataStorage.addProductToCatalog(product);
        Assertions.assertEquals(Optional.empty(), dataStorage.getProductById(2), "No product expected when ID doesn't match");
    }


}
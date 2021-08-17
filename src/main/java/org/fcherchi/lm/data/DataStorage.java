package org.fcherchi.lm.data;

import org.fcherchi.lm.data.entities.Product;

import java.util.Optional;

/**
 * Represents an abstraction of the data storage. It can be used to store data in memory, database, etc.
 */
public interface DataStorage {

    /**
     * Add one new type of article to the catalog.
     * @param productToAdd
     *      */
    void addProductToCatalog(Product productToAdd);


    Optional<Product> getProductById(Integer i);
}

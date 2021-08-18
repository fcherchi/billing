package org.fcherchi.lm.data;

import org.fcherchi.lm.data.entities.Product;
import org.fcherchi.lm.data.entities.ProductCategory;

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

    /**
     * Retrieves a product by its ID or empty if the product was not found.
     * @param productId
     * @return
     */
    Optional<Product> getProductById(Integer productId);

    /**
     * Retrieves the total amount of products in the catalog.
     * @return
     */
    int getProductsCount();

    /**
     * Adds or updates a new product category.
     * @param id
     * @param description
     */
    void addOrUpdateProductCategory(int id, String description);

    /**
     * Retrieves a category by its Id
     * @param id
     * @return
     */
    Optional<ProductCategory> getCategoryById(int id);
}

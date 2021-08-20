package org.fcherchi.lm.data;

import org.fcherchi.lm.entities.Product;
import org.fcherchi.lm.entities.ProductCategory;

import java.util.Optional;

/**
 * Represents an abstraction of the data storage. It can be used to store data in memory, database, etc.
 */
public interface DataStorage {

    /**
     * Add one new type of article to the catalog.
     * @param productToAdd the product to be added
     */
    void addProductToCatalog(Product productToAdd);

    /**
     * Retrieves a product by its ID or empty if the product was not found.
     * @param productId the product id
     * @return The product matching the given product ID or empty
     */
    Optional<Product> getProductById(Integer productId);

    /**
     * Retrieves the total amount of products in the catalog.
     * @return the total amount of products in the catalog.
     */
    int getProductsCount();

    /**
     * Adds or updates a new product category.
     * @param id The id of the product category being added / updated
     * @param description the description of the product category
     * @param isImported true if it is imported
     * @return The resultant ProductCategory
     */
    ProductCategory addOrUpdateProductCategory(int id, String description, Boolean isImported);

    /**
     * Retrieves a category by its Id
     * @param id The id to search a category
     * @return The category or none in case could not be found.
     */
    Optional<ProductCategory> getCategoryById(int id);
}

package org.fcherchi.lm.entities;

import java.util.Objects;

public class Product {


    /**
     * Unique identifier of the product
     */
    private final int id;

    /**
     * Description of the product
     */
    private final String description;

    /**
     * Category of the product
     */
    private final ProductCategory category;

    /**
     * Price
     */
    private final double price;

    /**
     * Creates a product instance
     * @param id The product Id
     * @param description The Description
     * @param category Reference to the category
     * @param price The price
     */
    public Product(int id, String description, ProductCategory category, double price) {
        this.id = id;
        this.description = description;
        this.category = category;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }



    @Override
    public int hashCode() {
        return Objects.hash(id, description, category, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && Double.compare(product.price, price) == 0 && Objects.equals(description, product.description) && Objects.equals(category, product.category);
    }
}

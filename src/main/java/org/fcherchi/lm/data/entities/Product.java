package org.fcherchi.lm.data.entities;

import java.util.Objects;

public class Product {


    /**
     * Unique identifier of the product
     */
    private Integer id;

    /**
     * Description of the product
     */
    private String description;

    /**
     * Category of the product
     */
    private ProductCategory category;

    /**
     * Price
     */
    private Double price;


    public Product(Integer id, String description, ProductCategory category, Double price) {
        this.id = id;
        this.description = description;
        this.category = category;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id) && description.equals(product.description) && category.equals(product.category) && price.equals(product.price);
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
}

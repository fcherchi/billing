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
    private Integer categoryId;

    /**
     * Price
     */
    private Double price;

    public Product(Integer id, String description, Integer categoryId, Double price) {
        this.id = id;
        this.description = description;
        this.categoryId = categoryId;
        this.price = price;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product good = (Product) o;
        return Objects.equals(id, good.id) && Objects.equals(description, good.description) && Objects.equals(categoryId, good.categoryId) && Objects.equals(price, good.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, categoryId, price);
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", categoryId=" + categoryId +
                ", price=" + price +
                '}';
    }
}

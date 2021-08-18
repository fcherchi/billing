package org.fcherchi.lm.data.entities;

import java.util.Objects;

public class ProductCategory {

    /** Unique identifier of the category */
    private Integer id;
    /** Description of the product */
    private String description;


    public ProductCategory(Integer id, String description) {
        this.id = id;
        this.description = description;
    }




    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductCategory category = (ProductCategory) o;
        return id.equals(category.id) && description.equals(category.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}

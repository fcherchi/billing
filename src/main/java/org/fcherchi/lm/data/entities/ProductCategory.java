package org.fcherchi.lm.data.entities;

import java.util.Objects;

public class ProductCategory {

    /** Unique identifier of the category */
    private Integer id;
    /** Description of the product */
    private String description;
    /** Is the article imported */
    private Boolean isImported;


    public ProductCategory(Integer id, String description, Boolean isImported) {
        this.id = id;
        this.description = description;
        this.isImported = isImported;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }


    public Boolean getImported() {
        return isImported;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", isImported=" + isImported +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductCategory category = (ProductCategory) o;
        return id.equals(category.id) && description.equals(category.description) && isImported.equals(category.isImported);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, isImported);
    }
}

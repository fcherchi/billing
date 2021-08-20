package org.fcherchi.lm.data.entities;

import java.util.Objects;

public class ProductCategory {

    /** Unique identifier of the category */
    private final Integer id;
    /** Description of the product */
    private final String description;
    /** Is the article imported */
    private final Boolean isImported;

    /**
     * Creates a product category instance
     * @param id The id of the product category
     * @param description The description
     * @param isImported True if is an imported product (used to calculate taxes)
     */
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

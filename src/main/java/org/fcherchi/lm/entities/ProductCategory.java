package org.fcherchi.lm.entities;

import java.util.Objects;

public class ProductCategory {

    /** Unique identifier of the category */
    private final int id;
    /** Description of the product */
    private final String description;
    /** Is the article imported */
    private final boolean isImported;

    /**
     * Creates a product category instance
     * @param id The id of the product category
     * @param description The description
     * @param isImported True if is an imported product (used to calculate taxes)
     */
    public ProductCategory(int id, String description, boolean isImported) {
        this.id = id;
        this.description = description;
        this.isImported = isImported;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }


    public boolean getImported() {
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
        ProductCategory that = (ProductCategory) o;
        return id == that.id && Objects.equals(description, that.description) && Objects.equals(isImported, that.isImported);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, isImported);
    }
}

package org.fcherchi.lm.data.entities;

import java.util.Objects;

public class ProductCategory {

    /** Unique identifier of the category */
    private Integer id;
    /** Description of the product */
    private String description;
    /** Sales rate. could be 0 if no sales rate is applicable to this category.*/
    private Double salesRate;
    /** Import Rate. Could be 0 if no Import rate is applicable to this category.*/
    private Double importRate;

    public ProductCategory(Integer id, String description, Double salesRate, Double importRate) {
        this.id = id;
        this.description = description;
        this.salesRate = salesRate;
        this.importRate = importRate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductCategory that = (ProductCategory) o;
        return Objects.equals(id, that.id) && Objects.equals(description, that.description) && Objects.equals(salesRate, that.salesRate) && Objects.equals(importRate, that.importRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, salesRate, importRate);
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Double getSalesRate() {
        return salesRate;
    }

    public Double getImportRate() {
        return importRate;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", salesRate=" + salesRate +
                ", importRate=" + importRate +
                '}';
    }
}

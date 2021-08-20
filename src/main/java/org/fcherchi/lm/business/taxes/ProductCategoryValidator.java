package org.fcherchi.lm.business.taxes;

/**
 * Abstraction to validate that a product category exists when used in the tax configuration *
 * It is used as a way to have IoC with data layer (which should not know about the business layer)
 */
public interface ProductCategoryValidator {
    /**
     * Implementors should return true when product category can be configured with tax exceptions
     * @param productCategoryId The product Category Id to verify
     * @return true when product category can be configured with tax exceptions, false if product cannot be configured (for example because it is not known(
     */
    boolean isProductCategoryConfigurable(int productCategoryId);
}

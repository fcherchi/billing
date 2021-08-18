package org.fcherchi.lm.business;

import org.fcherchi.lm.business.Basket;
import org.fcherchi.lm.business.exceptions.DataInconsistencyException;
import org.fcherchi.lm.data.entities.Product;
import org.fcherchi.lm.factory.EntitiesFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BasketTest {

    private Basket basket = new Basket();

    @Test
    void addBasketLine() {
        basket.addItemsToBasket(EntitiesFactory.createProductNoTax(1), 1.0);
        basket.addItemsToBasket(EntitiesFactory.createProductNoTax(2), 3.0);
        Assertions.assertEquals(4.0, basket.getTotalItemsInBasket(), "Amount of prods in basket should match");
        Assertions.assertEquals(2, basket.getNumberOfDifferentProductsInBasket(), "Amount of different products should match");
    }

    @Test
    void noNegativeQuantityAllowed() {
        Assertions.assertThrows(DataInconsistencyException.class, () -> {
            basket.addItemsToBasket(EntitiesFactory.createProductNoTax(1), -1.0);
        }, "Expected Exception if amount is negative");
    }

    @Test
    void adding2TimesSameProd() {
        Product p1 = EntitiesFactory.createProductNoTax(1);
        basket.addItemsToBasket(p1, 1.0);
        basket.addItemsToBasket(p1, 3.0);
        Assertions.assertEquals(4.0, basket.getTotalItemsInBasket(), "Expected items of to be added");
        Assertions.assertEquals(1, basket.getNumberOfDifferentProductsInBasket(), "Expected only 1 different product after adding twice the same");
    }

    @Test
    void adding2TimesSameProdWithOtherProdInBetween() {
        Product p1 = EntitiesFactory.createProductNoTax(1);
        Product p2 = EntitiesFactory.createProductNoTax(2);

        basket.addItemsToBasket(p1, 1.0);
        basket.addItemsToBasket(p2, 5.5);
        basket.addItemsToBasket(p1, 3.0);
        Assertions.assertEquals(9.5, basket.getTotalItemsInBasket(), "Expected items to be added");
        Assertions.assertEquals(2, basket.getNumberOfDifferentProductsInBasket(), "Expected only 1 different product after adding twice the same");
    }
}
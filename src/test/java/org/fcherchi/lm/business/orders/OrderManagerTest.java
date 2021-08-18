package org.fcherchi.lm.business.orders;


import org.fcherchi.lm.business.exceptions.DataInconsistencyException;
import org.fcherchi.lm.data.DataStorage;
import org.fcherchi.lm.data.entities.Product;
import org.fcherchi.lm.factory.EntitiesFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class OrderManagerTest {

    @Mock
    private DataStorage dataStorage;
    @Mock
    private Basket basket;

    @Test
    public void testAddItemToBasket() {
        OrderManager orderManager = new OrderManager(dataStorage, basket);
        Optional<Product> product1 = Optional.of(new Product(1, "p1", EntitiesFactory.getCategoryNoTaxes(), 22.25));
        Mockito.when(dataStorage.getProductById(1)).thenReturn(product1);
        Mockito.when(basket.getTotalItemsInBasket()).thenReturn(1.0);

        orderManager.addItem(1, 1.0);
        Mockito.verify(basket).addItemsToBasket(product1.get(), 1.0);
        Assertions.assertEquals(1, orderManager.getTotalItemsInBasket(), "Order manager should add items to basket");
    }

    @Test
    public void testExceptionWhenProductNotFound() {
        OrderManager orderManager = new OrderManager(dataStorage, basket);
        Mockito.when(dataStorage.getProductById(1)).thenReturn(Optional.empty());

        Assertions.assertThrows(DataInconsistencyException.class, () -> {
            orderManager.addItem(1, 1.0);
        }, "Exception expected if wrong product id is provided");
    }

}
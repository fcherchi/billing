package org.fcherchi.lm.orders;


import org.fcherchi.lm.data.DataStorage;
import org.fcherchi.lm.data.entities.Product;
import org.fcherchi.lm.orders.exceptions.DataInconsistencyException;
import org.fcherchi.lm.orders.impl.OrderManagerImpl;
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
        OrderManager orderManager = new OrderManagerImpl(dataStorage, basket);
        Optional<Product> product1 = Optional.of(new Product(1, "p1", 1, 22.25));
        Mockito.when(dataStorage.getProductById(1)).thenReturn(product1);
        Mockito.when(basket.getTotalItemsInBasket()).thenReturn(1.0);

        orderManager.addItem(1, 1.0);
        Mockito.verify(basket).addItemsToBasket(1, 1.0);
        Assertions.assertEquals(1, orderManager.getTotalItemsInBasket());
    }

    @Test
    public void testExceptionWhenProductNotFound() {
        OrderManager orderManager = new OrderManagerImpl(dataStorage, basket);
        Mockito.when(dataStorage.getProductById(1)).thenReturn(Optional.empty());

        Assertions.assertThrows(DataInconsistencyException.class, () -> {
            orderManager.addItem(1, 1.0);
        });
    }

}
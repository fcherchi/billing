package org.fcherchi.lm.business.orders;

import org.fcherchi.lm.business.taxes.ConfigurationProvider;
import org.fcherchi.lm.business.taxes.InMemoryConfigurationProvider;
import org.fcherchi.lm.business.taxes.ProductCategoryValidator;
import org.fcherchi.lm.data.DataStorage;
import org.fcherchi.lm.data.entities.Product;
import org.fcherchi.lm.data.entities.ProductCategory;
import org.fcherchi.lm.data.entities.Receipt;
import org.fcherchi.lm.data.impl.HashMapDataStorage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Integration test with all real objects attached and no mocks
 */
public class ReceiptGeneratorTest {

    //Categories PKs
    public static final int BOOKS_CATEGORY_ID = 1;
    public static final int CD_CATEGORY_ID = 2;
    public static final int FOOD_CATEGORY_ID = 3;
    public static final int MEDICAL_CATEGORY_ID = 4;
    public static final int IMPORTED_FOOD_CATEGORY_ID = 5;
    public static final int IMPORTED_PERFUME_CATEGORY_ID = 6;
    public static final int PERFUME_CATEGORY_ID = 7;

    //Products PKs
    public static final int BOOK_ID = 1;
    public static final int CD_ID = 2;
    public static final int CHOCOLATE_BAR_ID = 3;
    public static final int IMPORTED_BOX_CHOCOLATES_ID = 4;
    public static final int IMPORTED_BOTTLE_OF_PERFUME_EXPENSIVE_ID = 5;
    public static final int IMPORTED_BOTTLE_OF_PERFUME_CHEAP_ID = 6;
    public static final int BOTTLE_OF_PERFUME_ID = 7;
    public static final int PACKET_OF_HEADACHE_PILLS_ID = 8;
    public static final int BOX_OF_IMPORTED_CHOCOLATES_ID = 9;


    private static DataStorage dataStorage;
    private static ConfigurationProvider configurationProvider;
    private ReceiptLineGenerator receiptLineGenerator;
    private ReceiptGenerator receiptGenerator;

    @BeforeAll
    public static void initialSetup() {
       // System.out.println("Before all invoked!!");
        dataStorage = new HashMapDataStorage();
        initializeCategoriesAndProducts();
        configurationProvider = new InMemoryConfigurationProvider((ProductCategoryValidator) dataStorage);
        configurationProvider.initializeConfiguration();
    }

    private static void initializeCategoriesAndProducts() {
        //categories
        ProductCategory books = dataStorage.addOrUpdateProductCategory(BOOKS_CATEGORY_ID, "Books", false);
        ProductCategory cds = dataStorage.addOrUpdateProductCategory(CD_CATEGORY_ID, "Music CDs", false);
        ProductCategory food = dataStorage.addOrUpdateProductCategory(FOOD_CATEGORY_ID, "Food", false);
        ProductCategory medical = dataStorage.addOrUpdateProductCategory(MEDICAL_CATEGORY_ID, "Medical", false);
        ProductCategory importedFood = dataStorage.addOrUpdateProductCategory(IMPORTED_FOOD_CATEGORY_ID, "Imported Food", true);
        ProductCategory importedPerfume = dataStorage.addOrUpdateProductCategory(IMPORTED_PERFUME_CATEGORY_ID, "Imported Perfume", true);
        ProductCategory perfume = dataStorage.addOrUpdateProductCategory(PERFUME_CATEGORY_ID, "Perfume", false);

        //products
        dataStorage.addProductToCatalog(new Product(BOOK_ID, "Book", books, 12.49));
        dataStorage.addProductToCatalog(new Product(CD_ID, "Music CD", cds, 14.99));
        dataStorage.addProductToCatalog(new Product(CHOCOLATE_BAR_ID, "Chocolate Bar", food, 0.85));
        dataStorage.addProductToCatalog(new Product(IMPORTED_BOX_CHOCOLATES_ID, "Imported Box Of Chocolates", importedFood, 10.00));
        dataStorage.addProductToCatalog(new Product(IMPORTED_BOTTLE_OF_PERFUME_EXPENSIVE_ID, "Imported bottle of Perfume", importedPerfume, 47.50));
        dataStorage.addProductToCatalog(new Product(IMPORTED_BOTTLE_OF_PERFUME_CHEAP_ID, "Imported bottle of Perfume", importedPerfume, 27.99));
        dataStorage.addProductToCatalog(new Product(BOTTLE_OF_PERFUME_ID, "Bottle of Perfume", perfume, 18.99));
        dataStorage.addProductToCatalog(new Product(PACKET_OF_HEADACHE_PILLS_ID, "Packet of Headache pills", medical, 9.75));
        dataStorage.addProductToCatalog(new Product(BOX_OF_IMPORTED_CHOCOLATES_ID, "Box of imported Chocolates", food, 11.25));

    }

    @BeforeEach()
    public void setup() {
        this.receiptLineGenerator = new ReceiptLineGenerator(configurationProvider);
        this.receiptGenerator = new ReceiptGenerator(receiptLineGenerator);
    }

    @Test
    public void testReceiptCreation() {

        Receipt receipt1 = receiptGenerator.buildReceipt(getBasketInput1());
        Assertions.assertEquals(1.50, receipt1.getSalesTaxes(), "Expected sales taxes of 1.50 in output1");
        Assertions.assertEquals(29.83, receipt1.getTotal(), "Expected total of 29.83 in output1");

        System.out.println(receipt1.toString());

    }

    private Basket getBasketInput1() {
        Basket basket = new Basket();
        Product product = dataStorage.getProductById(BOOK_ID).get();
        basket.addItemsToBasket(dataStorage.getProductById(BOOK_ID).get(), 1.0);
        basket.addItemsToBasket(dataStorage.getProductById(CD_ID).get(), 1.0);
        basket.addItemsToBasket(dataStorage.getProductById(CHOCOLATE_BAR_ID).get(), 1.0);

        return basket;
    }
}

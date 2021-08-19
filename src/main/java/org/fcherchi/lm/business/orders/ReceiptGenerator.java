package org.fcherchi.lm.business.orders;

import org.fcherchi.lm.business.taxes.ConfigurationProvider;
import org.fcherchi.lm.business.taxes.TaxCalculator;
import org.fcherchi.lm.business.taxes.TaxConfiguration;
import org.fcherchi.lm.data.entities.BasketLine;
import org.fcherchi.lm.data.entities.ReceiptLine;
/**
 * Generates a Receipt out of a Basket
 */
public class ReceiptGenerator {

   private final ConfigurationProvider configurationProvider;

    public ReceiptGenerator(TaxConfiguration taxConfiguration, ConfigurationProvider configurationProvider) {
       this.configurationProvider = configurationProvider;
    }


    /**
     * Creates a receipt line with taxes out of a basket line (raw price)
     * @param basketLine
     * @return
     */
    public ReceiptLine buildReceiptLine(BasketLine basketLine) {
        //check nulls
        validateBasketLine(basketLine);

        TaxCalculator taxCalculator = new TaxCalculator();

        double price = basketLine.getProduct().getPrice();
        double endPrice = price;

//        if (this.configurationProvider.getConfiguration().getExceptionByProductCategoryId(basketLine.getProduct().getCategory().getId()).isEmpty()) {
//
//        }

        if (basketLine.getProduct().getCategory().getImported()) {
           endPrice = taxCalculator.getPricePlusTaxes(price,
                   0.0,
                   this.configurationProvider.getConfiguration().getImportTax());
        }
        return new ReceiptLine(basketLine, endPrice);
    }


    /**
     * Defensive method to throw exception if null detected on mandatory fields
     * @param basketLine
     */
    private void validateBasketLine(BasketLine basketLine) {
        if (basketLine.getProduct() == null) throw new IllegalArgumentException("Product in Basket Line cannot be null.");
        if (basketLine.getProduct().getCategory() == null) throw new IllegalArgumentException("ProductCategory in Product in  Basket Line cannot be null.");
    }
}

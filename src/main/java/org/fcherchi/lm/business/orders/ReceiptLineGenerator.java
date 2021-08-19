package org.fcherchi.lm.business.orders;

import org.fcherchi.lm.business.taxes.ConfigurationProvider;
import org.fcherchi.lm.business.taxes.TaxCalculator;
import org.fcherchi.lm.data.entities.BasketLine;
import org.fcherchi.lm.data.entities.ProductCategory;
import org.fcherchi.lm.data.entities.ReceiptLine;
import org.fcherchi.lm.data.entities.TaxException;

import java.util.Optional;

/**
 * Generates a Receipt out of a Basket
 */
public class ReceiptLineGenerator {

    private static final double NO_TAX = 0.00;
    private final ConfigurationProvider configurationProvider;

    public ReceiptLineGenerator(ConfigurationProvider configurationProvider) {
       this.configurationProvider = configurationProvider;
    }


    /**
     * Creates a receipt line with taxes out of a basket line (raw price)
     * @param basketLine The basket line to use as base.
     * @return The receipt line with taxes applied
     */
    public ReceiptLine buildReceiptLine(BasketLine basketLine) {
        //check nulls
        validateBasketLine(basketLine);
        TaxCalculator taxCalculator = new TaxCalculator();
        double price = basketLine.getProduct().getPrice();

        //could get 0.0 if exempt
        double applicableSalesRate = getApplicableSalesRate(basketLine.getProduct().getCategory());
        double applicableImportRate = getApplicableImportRate(basketLine.getProduct().getCategory());

        double endPrice = taxCalculator.getPricePlusTaxes(price,
                applicableSalesRate,
                applicableImportRate,
                basketLine.getQuantity());

        return new ReceiptLine(basketLine, endPrice);
    }

    /**
     * Gets the applicable import rate if any
     * @param category The category
     * @return 0 if no import product provided, otherwise, the import tax rate as per config.
     */
    private double getApplicableImportRate(ProductCategory category) {
        //shortcut for not imported products. Ignore import tax value in tax exception
        if (! category.getImported()) {
            return NO_TAX;
        }
        //default tax rate
        double result = this.configurationProvider.getConfiguration().getImportTax() ;
        Optional<TaxException> taxException = this.configurationProvider.getConfiguration().getExceptionByProductCategoryId(category.getId());

        if (taxException.isPresent() && taxException.get().getImportTax().isPresent() ) {
            result = taxException.get().getImportTax().get();
        }
        return result;
    }

    private double getApplicableSalesRate(ProductCategory category) {
        //default tax rate
        double result = this.configurationProvider.getConfiguration().getSalesTax();
        Optional<TaxException> taxException = this.configurationProvider.getConfiguration().getExceptionByProductCategoryId(category.getId());

        if (taxException.isPresent() && taxException.get().getSalesTax().isPresent()) {
            result = taxException.get().getSalesTax().get();
        }
        return result;
    }

    /**
     * Defensive method to throw exception if null detected on mandatory fields
     * @param basketLine the basket line to validate
     */
    private void validateBasketLine(BasketLine basketLine) {
        if (basketLine.getProduct() == null) throw new IllegalArgumentException("Product in Basket Line cannot be null.");
        if (basketLine.getProduct().getCategory() == null) throw new IllegalArgumentException("ProductCategory in Product in  Basket Line cannot be null.");
    }
}

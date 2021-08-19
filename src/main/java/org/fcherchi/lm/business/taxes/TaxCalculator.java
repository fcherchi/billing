package org.fcherchi.lm.business.taxes;

import org.fcherchi.lm.data.exceptions.BadConfigurationException;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Calculates the taxes according to the price given by configuration
 */
public class TaxCalculator {

    public static final int SALES_TAX_ROUND_UP_PRECISION = 2;
    private static final double ROUNDUP_FACTOR = 0.05;

    /**
     * Gets the final price adding both import taxes and sales taxes
     * @param rawPrice The price without taxes
     * @param salesTax The Sales Tax amount
     * @param importTax the Import Tax amount
     * @return The price plus sales and import taxes
     */
    public double getPricePlusTaxes(double rawPrice, double salesTax, double importTax, double quantity) {
        //validate no negatives
        validateRates(salesTax, importTax);

        BigDecimal rawPriceBD = new BigDecimal(Double.toString(rawPrice));
        BigDecimal rawPriceTimesQuantity = rawPriceBD.multiply(new BigDecimal(Double.toString(quantity)));

        BigDecimal result = rawPriceTimesQuantity
                .add(new BigDecimal(Double.toString(salesTax)))
                .add(new BigDecimal(Double.toString(importTax)));

        return result.doubleValue();
    }

    private void validateRates(double salesTax, double importTax) {
        if (salesTax < 0) {
            throw new BadConfigurationException(String.format("Negative Sales Tax Rate detected in configuration: '%.2f')", salesTax));
        }
        if (importTax < 0) {
            throw new BadConfigurationException(String.format("Negative Import Tax Rate detected in configuration: '%.2f')", importTax));
        }
    }


    /**
     * Gets the sales tax of the given price.
     * @param price The price without taxes
     * @return The Sales tax part to be added to the received price
     */
    public double getTaxFor(double price, double taxRate, double quantity) {
        BigDecimal priceBD = new BigDecimal(Double.toString(price));
        BigDecimal taxRateBD = new BigDecimal(Double.toString(taxRate));

        return getTax(priceBD, taxRateBD).multiply(new BigDecimal(quantity)).doubleValue();
    }

    /**
     * Gets the applicable tax rounded up to the nearest 0.05
     * @param value  The price to be taxed
     * @param taxRate The tax rate
     * @return The tax part of the price
     */
    private BigDecimal getTax(BigDecimal value, BigDecimal taxRate) {
        BigDecimal taxBD = value.multiply(taxRate).divide(new BigDecimal(100));
        return round(taxBD);
    }

    /**
     * Rounds up to the nearest 0.05 (i.e. 2.31 -> 2.35)
     * @param value the value to round up
     * @return the rounded up value.
     */
    private BigDecimal round(BigDecimal value) {

        //this line truncates the value to 2 decimals
        value = value.setScale(SALES_TAX_ROUND_UP_PRECISION, RoundingMode.FLOOR);

        //only round up factor of the form 0.0x are allowed. In the example the divisible factor is 5
        int divisibleFactor = (int) (ROUNDUP_FACTOR * 100);

        //we get the power of 10 we have to use as multiplier in order to remove the decimal separator
        long multiplier = (long) Math.pow(10, SALES_TAX_ROUND_UP_PRECISION);
        long valueWithoutDecimals = (long) (value.doubleValue() * multiplier);

        //we get the modulus by divide to the divisible factor. i.e. from 1352 we get 2
        int mod = (int) (valueWithoutDecimals % divisibleFactor);
        // if not a round number (multiple of 5)
        if (mod != 0) {
            // go to the next multiple of 5 (from 1352 we do 1352 + (5 - 2)
            valueWithoutDecimals = (int) (valueWithoutDecimals + divisibleFactor - mod);
        }
        return new BigDecimal(valueWithoutDecimals).divide(new BigDecimal(multiplier));
    }

}

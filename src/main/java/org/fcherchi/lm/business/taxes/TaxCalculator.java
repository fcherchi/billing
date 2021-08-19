package org.fcherchi.lm.business.taxes;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Calculates the taxes according to the price given by configuration
 */
public class TaxCalculator {

    public static final int SALES_TAX_ROUND_UP_PRECISION = 2;
    private static final double ROUNDUP_FACTOR = 0.05;

    private final TaxConfiguration taxConfiguration;

    public TaxCalculator(TaxConfiguration taxConfiguration) {
        this.taxConfiguration = taxConfiguration;
    }

    /**
     * Gets the price adding the sales tax accoring to tax configuration
     * @param price The price without taxes
     * @return The price plus sales taxes
     */
    public double getPricePlusSalesTax(double price) {
        BigDecimal bd = new BigDecimal(Double.toString(price));
        BigDecimal result = bd.add(new BigDecimal(Double.toString(getSalesTaxFor(price))));

        return result.doubleValue();
    }

    /**
     * Gets the price adding the import tax to the given raw price.
     * @param rawPrice The price without taxes
     * @return
     */
    public double getPricePlusImportTax(double rawPrice) {
        return rawPrice + getImportTaxFor(rawPrice);
    }

    /**
     * Gets the final price adding both import taxes and sales taxes
     * @param rawPrice The price without taxes
     * @return The price plus sales and import taxes
     */
    public double getPricePlusSalesAndImportTax(double rawPrice) {
        return rawPrice + getImportTaxFor(rawPrice) + getSalesTaxFor(rawPrice);
    }


    /**
     * Gets the sales tax of the given price.
     * @param price The price without taxes
     * @return The Sales tax part to be added to the received price
     */
    public double getSalesTaxFor(double price) {
        return getTax(price, this.taxConfiguration.getSalesTax());
    }

    /**
     * Gets the import tax of the given price.
     * @param price The price without taxes
     * @return The Import tax part to be added to the received price
     */
    public double getImportTaxFor(double price) {
        return getTax(price, this.taxConfiguration.getImportTax());
    }


    /**
     * Gets the applicable tax rounded up to the nearest 0.05
     * @param value  The price to be taxed
     * @param taxRate The tax rate
     * @return The tax part of the price
     */
    private double getTax(double value, double taxRate) {
        return round(value*taxRate/100);
    }

    /**
     * Rounds up to the nearest 0.05 (i.e. 2.31 -> 2.35)
     * @param value the value to round up
     * @return the rounded up value.
     */
    private double round(double value) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        //this line truncates the value to 2 decimals
        bd = bd.setScale(SALES_TAX_ROUND_UP_PRECISION, RoundingMode.FLOOR);

        //only round up factor of the form 0.0x are allowed. In the example the divisible factor is 5
        int divisibleFactor = (int) (ROUNDUP_FACTOR * 100);

        //we get the power of 10 we have to use as multiplier in order to remove the decimal separator
        long multiplier = (long) Math.pow(10, SALES_TAX_ROUND_UP_PRECISION);
        long valueWithoutDecimals = (long) (bd.doubleValue() * multiplier);

        //we get the modulus by divide to the divisible factor. i.e. from 1352 we get 2
        int mod = (int) (valueWithoutDecimals % divisibleFactor);
        // if not a round number (multiple of 5)
        if (mod != 0) {
            // go to the next multiple of 5 (from 1352 we do 1352 + (5 - 2)
            valueWithoutDecimals = (int) (valueWithoutDecimals + divisibleFactor - mod);
        }
        return (double) valueWithoutDecimals / multiplier;
    }

}

package org.fcherchi.lm.business.taxes;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TaxCalculator {

    public static final int PRICE_DECIMAL_PRECISION = 2;
    public static final int SALES_TAX_ROUND_UP_PRECISION = 2;
    private final TaxConfiguration taxConfiguration;

    public TaxCalculator(TaxConfiguration taxConfiguration) {
        this.taxConfiguration = taxConfiguration;
    }

    public double getPricePlusSalesTax(double price) {
        return truncate(price + getSalesTaxFor(price));
    }

    public double getImportTaxFor(double price) {
        return getTax(price, this.taxConfiguration.getImportTax());
    }

    public double getPricePlusImportTax(double rawPrice) {
        return truncate(rawPrice + getImportTaxFor(rawPrice));
    }

    public double getSalesTaxFor(double price) {
        return getTax(price, this.taxConfiguration.getSalesTax());
    }

    private double getTax(double value, double taxRate) {
        return round(value*taxRate/100);
    }

    private double round(double value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(SALES_TAX_ROUND_UP_PRECISION, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private double truncate(double value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(PRICE_DECIMAL_PRECISION, RoundingMode.DOWN);
        return bd.doubleValue();
    }
}

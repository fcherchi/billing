package org.fcherchi.lm.business.taxes.impl;

import org.fcherchi.lm.business.taxes.ConfigurationProvider;
import org.fcherchi.lm.business.taxes.TaxConfiguration;

/**
 * Implementation that hardcodes the configuration in memory. To be replaced by a file config.
 */
public class InMemoryConfigurationProvider implements ConfigurationProvider {

    private TaxConfiguration taxConfiguration;

    @Override
    public void initializeConfiguration() {

    }

    @Override
    public TaxConfiguration getConfiguration() {
        return this.taxConfiguration;
    }
}

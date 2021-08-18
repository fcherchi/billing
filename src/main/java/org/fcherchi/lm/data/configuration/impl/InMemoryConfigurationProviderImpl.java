package org.fcherchi.lm.data.configuration.impl;

import org.fcherchi.lm.data.configuration.ConfigurationProvider;
import org.fcherchi.lm.data.configuration.TaxConfiguration;

/**
 * Implementation that hardcodes the configuration in memory. To be replaced by a file config.
 */
public class InMemoryConfigurationProviderImpl implements ConfigurationProvider {

    private TaxConfiguration taxConfiguration;

    @Override
    public void initializeConfiguration() {

    }

    @Override
    public TaxConfiguration getConfiguration() {
        return this.taxConfiguration;
    }
}

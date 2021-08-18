package org.fcherchi.lm.business.taxes;

public interface ConfigurationProvider {
    /**
     * Initializes the configuration. In File Config Provider, it would read from file the configuration
     */
    void initializeConfiguration();

    /**
     * Gets the configuration if loaded, or null if not.
     * @return
     */
    TaxConfiguration getConfiguration();
}

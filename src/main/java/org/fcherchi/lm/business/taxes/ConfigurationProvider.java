package org.fcherchi.lm.business.taxes;

/**
 * Abstraction to initialize and obtain the configuration
 */
public interface ConfigurationProvider {
    /**
     * Initializes the configuration. In File Config Provider, it would read from file the configuration
     */
    void initializeConfiguration();

    /**
     *
     * @return the configuration if loaded, or null if not.
     */
    TaxConfiguration getConfiguration();
}

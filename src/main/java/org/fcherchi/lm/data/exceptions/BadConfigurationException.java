package org.fcherchi.lm.data.exceptions;

/**
 * To be thrown when bad configuration has been detected
 */
public class BadConfigurationException extends RuntimeException {
    public BadConfigurationException (String message) {
        super(message);
    }

    public BadConfigurationException (String message, Throwable inner) {
        super(message, inner);
    }
}

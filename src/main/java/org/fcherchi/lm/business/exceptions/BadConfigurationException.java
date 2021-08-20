package org.fcherchi.lm.business.exceptions;

/**
 * To be thrown when bad configuration has been detected
 */
public class BadConfigurationException extends RuntimeException {
    public BadConfigurationException (String message) {
        super(message);
    }
}

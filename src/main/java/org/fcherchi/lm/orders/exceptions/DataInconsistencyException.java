package org.fcherchi.lm.orders.exceptions;

/**
 * To be thrown when data could become inconsistent (i.e. referential integrity violated)
 */
public class DataInconsistencyException extends RuntimeException {
    public DataInconsistencyException(String message) {
        super(message);
    }
}

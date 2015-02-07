package com.swcguild.flooringmastery.exceptions;

/**
 *
 * @author Michael J. Gulley & Zach Sullivan
 */
public class DataReadException extends Exception {

    public DataReadException() {
        super();
    }

    public DataReadException(String message) {
        super(message);
    }

    public DataReadException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataReadException(Throwable cause) {
        super(cause);
    }

}

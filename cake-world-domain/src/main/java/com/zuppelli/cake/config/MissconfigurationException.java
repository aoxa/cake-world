package com.zuppelli.cake.config;

public class MissconfigurationException extends RuntimeException {
    public MissconfigurationException() {
        super();
    }

    public MissconfigurationException( String message ) {
        super( message );
    }
}

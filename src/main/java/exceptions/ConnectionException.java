package exceptions;

import java.io.IOException;

public class ConnectionException extends RuntimeException {

    public ConnectionException(IOException e) {
        super("Connection failed: " + e.getMessage(), e);
    }
}

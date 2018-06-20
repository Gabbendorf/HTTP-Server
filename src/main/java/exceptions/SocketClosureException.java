package exceptions;

import java.io.IOException;

public class SocketClosureException extends RuntimeException {

    public SocketClosureException(IOException e) {
        super("Could not close the socket: " + e.getMessage(), e);
    }
}

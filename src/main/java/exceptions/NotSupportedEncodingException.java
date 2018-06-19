package exceptions;

import java.io.UnsupportedEncodingException;

public class NotSupportedEncodingException extends RuntimeException {

    public NotSupportedEncodingException(UnsupportedEncodingException e) {
        super("Encoding not valid: " + e.getMessage(), e);
    }
}

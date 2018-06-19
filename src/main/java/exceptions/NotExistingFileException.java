package exceptions;

import java.io.FileNotFoundException;

public class NotExistingFileException extends RuntimeException {

    public NotExistingFileException(FileNotFoundException e) {
        super("Could not find requested file: " + e.getMessage(), e);
    }
}

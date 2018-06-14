package exceptions;

import java.io.FileNotFoundException;

public class FileException extends RuntimeException {

    public FileException(FileNotFoundException e) {
        super("Could not find requested file: " + e.getMessage(), e);
    }
}

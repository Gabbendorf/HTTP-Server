package controllers.fileSystem;

import exceptions.NotExistingFileException;
import exceptions.InputStreamException;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class FileSystemTest {

    @Test(expected = NotExistingFileException.class)
    public void throwsFileException() {
        FileSystemWithNotExistingFileException fileSystemWithNotExistingFileException = new FileSystemWithNotExistingFileException();

        fileSystemWithNotExistingFileException.writeTo("hi", "/here");
    }

    @Test(expected = InputStreamException.class)
    public void throwsInputStreamException() {
        FileSystemWithInputStreamException fileSystemWithInputStreamException = new FileSystemWithInputStreamException();

        fileSystemWithInputStreamException.readContentFor("/here");
    }

    private class FileSystemWithNotExistingFileException extends FileSystem {

        @Override
        public void writeTo(String content, String path) {
            throw new NotExistingFileException(new FileNotFoundException());
        }

    }

    private class FileSystemWithInputStreamException extends FileSystem {

        @Override
        public String readContentFor(String path) {
            throw new InputStreamException(new IOException());
        }
    }
}
package controllers.fileSystem;

import exceptions.FileException;
import exceptions.InputStreamException;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class FileSystemTest {

    @Test(expected = FileException.class)
    public void throwsFileException() {
        FileSystemWithFileException fileSystemWithFileException = new FileSystemWithFileException();

        fileSystemWithFileException.writeTo("hi", "/here");
    }

    @Test(expected = InputStreamException.class)
    public void throwsInputStreamException() {
        FileSystemWithInputStreamException fileSystemWithInputStreamException = new FileSystemWithInputStreamException();

        fileSystemWithInputStreamException.readContentFor("/here");
    }

    private class FileSystemWithFileException extends FileSystem {

        @Override
        public void writeTo(String content, String path) {
            throw new FileException(new FileNotFoundException());
        }

    }

    private class FileSystemWithInputStreamException extends FileSystem {

        @Override
        public String readContentFor(String path) {
            throw new InputStreamException(new IOException());
        }
    }
}
package controllers.fileSystem;

import exceptions.NotExistingFileException;
import exceptions.InputStreamException;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class FileSystemTest {

    @Test
    public void listsRootContent() {
        FileSystem fileSystem = new FileSystem("src/test/java/root");

        String rootContent = fileSystem.rootContent();

        assertEquals("file.txt\nfile2.txt", rootContent);
    }

    @Test(expected = NotExistingFileException.class)
    public void throwsFileException() {
        FileSystemWithNotExistingFileException fileSystemWithNotExistingFileException = new FileSystemWithNotExistingFileException("");

        fileSystemWithNotExistingFileException.writeTo("hi", "/here");
    }

    @Test(expected = InputStreamException.class)
    public void throwsInputStreamException() {
        FileSystemWithInputStreamException fileSystemWithInputStreamException = new FileSystemWithInputStreamException("");

        fileSystemWithInputStreamException.readContentFor("/here");
    }

    private class FileSystemWithNotExistingFileException extends FileSystem {

        public FileSystemWithNotExistingFileException(String directory) {
            super(directory);
        }

        @Override
        public void writeTo(String content, String path) {
            throw new NotExistingFileException(new FileNotFoundException());
        }

    }

    private class FileSystemWithInputStreamException extends FileSystem {

        public FileSystemWithInputStreamException(String directory) {
            super(directory);
        }

        @Override
        public String readContentFor(String path) {
            throw new InputStreamException(new IOException());
        }
    }
}
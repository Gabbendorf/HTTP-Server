package controllers.fileSystem;

import exceptions.NotExistingFileException;
import exceptions.InputStreamException;
import exceptions.NotSupportedEncodingException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileSystem {

    private PrintWriter writer = null;
    private String directory;

    public FileSystem(String directory) {
        this.directory = directory;
    }

    public String readContentFor(String path) {
        String content;
        try {
            content = new String(Files.readAllBytes(Paths.get(directory + path)));
        } catch (IOException e) {
            throw new InputStreamException(e);
        }
        return content.trim();
    }

    public void writeTo(String content, String path) {
        try {
            try {
                writer = new PrintWriter(file(path), "UTF-8");
                writer.println(content);
                writer.flush();
            } catch (UnsupportedEncodingException e) {
                throw new NotSupportedEncodingException(e);
            } finally {
                tryCloseWriter();
            }
        } catch (FileNotFoundException e) {
            throw new NotExistingFileException(e);
        }
    }

    private void tryCloseWriter() {
        if (writer != null) {
            writer.close();
        }
    }

    private File file(String path) {
        File file = new File(directory + path);
        file.getParentFile().mkdirs();
        return file;
    }
}

package controllers.fileSystem;

import exceptions.NotExistingFileException;
import exceptions.InputStreamException;
import exceptions.NotSupportedEncodingException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileSystem {

    private final static String ABSOLUTE_PATH = "/Users/gabi/Desktop/MyApprenticeship/IPM/Java/HTTPServer/cob_spec/public/";
    private PrintWriter writer;

    public String readContentFor(String path) {
        String content;
        try {
            content = new String(Files.readAllBytes(Paths.get(ABSOLUTE_PATH + fileName(path))));
        } catch (IOException e) {
            throw new InputStreamException(e);
        }
        return content.trim();
    }

    public void writeTo(String content, String path) {
        try {
            try {
                writer = new PrintWriter(ABSOLUTE_PATH + fileName(path), "UTF-8");
                writer.println(content);
                writer.flush();
            } catch (UnsupportedEncodingException e) {
                throw new NotSupportedEncodingException(e);
            } finally {
                writer.close();
            }
        } catch (FileNotFoundException e) {
            throw new NotExistingFileException(e);
        }
    }

    private String fileName(String path) {
        return new StringBuilder(path).deleteCharAt(0).toString();
    }
}

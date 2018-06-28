package controllers.fileSystem;

import exceptions.NotExistingFileException;
import exceptions.InputStreamException;
import exceptions.NotSupportedEncodingException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileSystem {

    private PrintWriter writer = null;
    private String root;

    public FileSystem(String root) {
        this.root = root;
    }

    public String readContentFor(String path) {
        String content;
        try {
            content = new String(Files.readAllBytes(Paths.get(root + path)));
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

    private File[] rootFiles() {
        return new File(root).listFiles();
    }

    public List<String> rootFilesNames() {
        List<String> filesNames = new ArrayList<>();
        for (File file : rootFiles()) {
            filesNames.add(file.getName());
        }
        return filesNames;
    }

    public String rootContent() {
        return String.join("\n", rootFilesNames());
    }

    private void tryCloseWriter() {
        if (writer != null) {
            writer.close();
        }
    }

    private File file(String path) {
        File file = new File(root + path);
        file.getParentFile().mkdirs();
        return file;
    }

    public boolean fileDoesNotExist(String path) {
        String fileName = path.split("/")[1];
        return !rootFilesNames().contains(fileName);
    }

    public void deleteFile(String path) {
        File fileToDelete = new File(root + path);
        fileToDelete.delete();
    }
}

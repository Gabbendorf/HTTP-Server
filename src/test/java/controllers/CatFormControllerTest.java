package controllers;

import controllers.fileSystem.FileSystem;
import org.junit.Before;
import org.junit.Test;
import request.HTTPMethod;
import request.HTTPPath;
import request.HTTPRequest;
import response.HTTPResponse;

import static org.junit.Assert.*;
import static request.HTTPMethod.*;
import static response.StatusLine.CREATED;
import static response.StatusLine.NOT_FOUND;
import static response.StatusLine.OK;

public class CatFormControllerTest {

    private FileSystem fileSystemWithRootPath;
    private FileSystem fileSystemWithFilesDirectoryPath;

    @Before
    public void createInstances() {
        fileSystemWithRootPath = new FileSystem("src/test/java/root");
        fileSystemWithFilesDirectoryPath = new FileSystem("src/test/java/controllers/filesDirectory");
    }

    @Test
    public void respondsWithNotFoundForNotExistingFile() {
        CatFormController catFormController = new CatFormController(fileSystemWithRootPath);

        HTTPResponse response = catFormController.get(new HTTPRequest(GET.method, new HTTPPath("/something")));

        assertEquals(NOT_FOUND.message, response.getStatusLine());
    }

    @Test
    public void respondsWithOkAndFileContentInBody() {
        CatFormController catFormController = new CatFormController(fileSystemWithRootPath);

        HTTPResponse response = catFormController.get(new HTTPRequest(GET.method, new HTTPPath("/file.txt")));

        assertEquals(OK.message, response.getStatusLine());
        assertEquals("hi", response.getBody());
    }

    @Test
    public void createsNewFile() {
        CatFormController catFormController = new CatFormController(fileSystemWithFilesDirectoryPath);

        HTTPResponse response = catFormController.post(request(POST, "/cat-form", "data=something"));

        assertEquals(CREATED.message, response.getStatusLine());
    }

    @Test
    public void writesBodyContentIntoNewFile() {
        CatFormController catFormController = new CatFormController(fileSystemWithFilesDirectoryPath);

        catFormController.post(request(POST, "/cat-form", "data=something"));

        assertEquals("data=something", fileSystemWithFilesDirectoryPath.readContentFor("/data"));
    }

    @Test
    public void hasHeaderLocationWithNewFilePath() {
        CatFormController catFormController = new CatFormController(fileSystemWithFilesDirectoryPath);

        HTTPResponse response = catFormController.post(request(POST, "cat-form", "data=something"));

        assertEquals("Location: /cat-form/data", response.getHeaders());
    }

    @Test
    public void changesFileContent() {
        CatFormController catFormController = new CatFormController(fileSystemWithFilesDirectoryPath);

        catFormController.put(request(PUT, "/data", "data=something_else"));

        assertEquals("data=something_else", fileSystemWithFilesDirectoryPath.readContentFor("/data"));
    }

    @Test
    public void respondsWithOk() {
        CatFormController catFormController = new CatFormController(fileSystemWithFilesDirectoryPath);

        HTTPResponse response = catFormController.put(request(PUT, "/data", "data=something_else"));

        assertEquals(OK.message, response.getStatusLine());
    }

    @Test
    public void deletesFileAndRespondsWithOk() {
        HTTPRequest request = new HTTPRequest(DELETE.method, new HTTPPath("/data"));

        HTTPResponse response = new CatFormController(fileSystemWithFilesDirectoryPath).delete(request);

        assertEquals(OK.message, response.getStatusLine());
        assertTrue(fileSystemWithFilesDirectoryPath.fileDoesNotExist(request.getPath()));
    }

    private HTTPRequest request(HTTPMethod method, String path, String body) {
        return new HTTPRequest(method.method, new HTTPPath(path), body);
    }
}
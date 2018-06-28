package controllers.fileSystem;

import org.junit.Before;
import org.junit.Test;
import request.HTTPPath;
import request.HTTPRequest;
import request.RequestParser;
import response.HTTPResponse;

import java.util.Arrays;
import java.util.Map;

import static request.HTTPMethod.GET;
import static request.HTTPMethod.PATCH;
import static org.junit.Assert.*;
import static response.StatusLine.NO_CONTENT;
import static response.StatusLine.OK;
import static response.StatusLine.PRECONDITION_FAILED;

public class FileControllerTest {

    private FileSystem fileSystem;
    private FileControllerFake fileControllerFake;

    @Before
    public void createInstance() {
        fileSystem = new FileSystem("src/test/java/root");
        fileControllerFake = new FileControllerFake(fileSystem);
        fileSystem.writeTo("Hello", "/file.txt");
    }

    @Test
    public void respondsWithOkAndFileContentToGetRequest() {
        FileController fileController = new FileController(fileSystem);

        HTTPResponse response = fileController.get(new HTTPRequest(GET.method, new HTTPPath("/file.txt")));

        assertEquals(OK.message, response.getStatusLine());
        assertEquals("Hello", response.getBody());
    }

    @Test
    public void respondsWithNoContentForMatchingEtagInPatchRequest() {
        fileControllerFake.setEtag("12");

        HTTPResponse response = fileControllerFake.patch(requestWithEtagAndBody("/file.txt","12", "hi"));

        assertEquals(NO_CONTENT.message, response.getStatusLine());
    }

    @Test
    public void writesBodyContentToFileForMatchingEtagInPatchRequest() {
        fileControllerFake.setEtag("12");

        fileControllerFake.patch(requestWithEtagAndBody("/file.txt","12", "hi"));

        assertEquals("hi", fileSystem.readContentFor("/file.txt"));
    }

    @Test
    public void respondsWithPreconditionFailedForNotMatchingEtagInPatchRequest() {
        fileControllerFake.setEtag("12");

        HTTPResponse response = fileControllerFake.patch(requestWithEtagAndBody("/file.txt","10", "hi"));

        assertEquals(PRECONDITION_FAILED.message, response.getStatusLine());
    }

    @Test
    public void doesNotChangeFileContentIfNotMatchingEtagInPatchRequest() {
        fileControllerFake.setEtag("12");

        fileControllerFake.patch(requestWithEtagAndBody("/file.txt","10", "hi"));

        assertEquals("Hello", fileSystem.readContentFor("/file.txt"));
    }

    private HTTPRequest requestWithEtagAndBody(String path, String etag, String body) {
        return new HTTPRequest(PATCH.method, new HTTPPath(path), headers("If-Match", etag), body);
    }

    private Map<String, String> headers(String ifMatchHeader, String etag) {
        RequestParser parser = new RequestParser();
        return parser.headers(Arrays.asList(String.format("%s: %s", ifMatchHeader, etag)));
    }
}


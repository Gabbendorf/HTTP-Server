package controllers.fileSystem;

import org.junit.Before;
import org.junit.Test;
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

public class FilePageTest {

    private FileSystem fileSystem;
    private FilePageStub filePageStub;

    @Before
    public void createInstance() {
        fileSystem = new FileSystem("src/test/java/root");
        filePageStub = new FilePageStub(fileSystem);
        fileSystem.writeTo("Hello", "/file.txt");
    }

    @Test
    public void respondsWithOkAndFileContentToGetRequest() {
        FilePage filePage = new FilePage(fileSystem);

        HTTPResponse response = filePage.get(new HTTPRequest(GET.method, "/file.txt"));

        assertEquals(OK.message, response.getStatusLine());
        assertEquals("Hello", response.getBody());
    }

    @Test
    public void respondsWithNoContentForMatchingEtagInPatchRequest() {
        filePageStub.setEtag("12");

        HTTPResponse response = filePageStub.patch(requestWithEtagAndBody("/file.txt","12", "hi"));

        assertEquals(NO_CONTENT.message, response.getStatusLine());
    }

    @Test
    public void writesBodyContentToFileForMatchingEtagInPatchRequest() {
        filePageStub.setEtag("12");

        filePageStub.patch(requestWithEtagAndBody("/file.txt","12", "hi"));

        assertEquals("hi", fileSystem.readContentFor("/file.txt"));
    }

    @Test
    public void respondsWithPreconditionFailedForNotMatchingEtagInPatchRequest() {
        filePageStub.setEtag("12");

        HTTPResponse response = filePageStub.patch(requestWithEtagAndBody("/file.txt","10", "hi"));

        assertEquals(PRECONDITION_FAILED.message, response.getStatusLine());
    }

    @Test
    public void doesNotChangeFileContentIfNotMatchingEtagInPatchRequest() {
        filePageStub.setEtag("12");

        filePageStub.patch(requestWithEtagAndBody("/file.txt","10", "hi"));

        assertEquals("Hello", fileSystem.readContentFor("/file.txt"));
    }

    private HTTPRequest requestWithEtagAndBody(String path, String etag, String body) {
        return new HTTPRequest(PATCH.method, path, headers("If-Match", etag), body);
    }

    private Map<String, String> headers(String ifMatchHeader, String etag) {
        RequestParser parser = new RequestParser();
        return parser.headers(Arrays.asList(String.format("%s: %s", ifMatchHeader, etag)));
    }
}


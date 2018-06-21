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
    private FileSystemStub fileSystemStub;

    @Before
    public void createInstance() {
        fileSystem = new FileSystem();
        fileSystemStub = new FileSystemStub();
    }

    @Test
    public void respondsWithOkAndFileContentToGetRequest() {
        FilePage filePage = new FilePage(fileSystem);

        HTTPResponse response = filePage.get(new HTTPRequest(GET.method, "/text-file.txt"));

        assertEquals(OK.message, response.getStatusLine());
        assertEquals("file1 contents", response.getBody());
    }

    @Test
    public void respondsWithNoContentForMatchingEtagInPatchRequest() {
        FilePageStub filePageStub = new FilePageStub(fileSystemStub);
        filePageStub.setEtag("12");

        HTTPResponse response = filePageStub.patch(requestWithEtagAndBody("/","12", "hi"));

        assertEquals(NO_CONTENT.message, response.getStatusLine());
    }

    @Test
    public void writesBodyContentToFileForMatchingEtagInPatchRequest() {
        FilePageStub filePageStub = new FilePageStub(fileSystemStub);
        filePageStub.setEtag("12");

        filePageStub.patch(requestWithEtagAndBody("/","12", "hi"));

        assertEquals("hi", fileSystemStub.readContentFor("/"));
    }

    @Test
    public void respondsWithPreconditionFailedForNotMatchingEtagInPatchRequest() {
        FilePageStub filePageStub = new FilePageStub(fileSystemStub);
        filePageStub.setEtag("12");

        HTTPResponse response = filePageStub.patch(requestWithEtagAndBody("/","10", "hi"));

        assertEquals(PRECONDITION_FAILED.message, response.getStatusLine());
    }

    @Test
    public void doesNotChangeFileContentIfNotMatchingEtagInPatchRequest() {
        FilePageStub filePageStub = new FilePageStub(fileSystemStub);
        filePageStub.setEtag("12");

        filePageStub.patch(requestWithEtagAndBody("/","10", "hi"));

        assertEquals("ciao", fileSystemStub.readContentFor("/"));
    }

    private HTTPRequest requestWithEtagAndBody(String path, String etag, String body) {
        return new HTTPRequest(PATCH.method, path, headers("If-Match", etag), body);
    }

    private Map<String, String> headers(String ifMatch, String etag) {
        RequestParser parser = new RequestParser();
        return parser.headers(Arrays.asList(String.format("%s: %s", ifMatch, etag)));
    }
}


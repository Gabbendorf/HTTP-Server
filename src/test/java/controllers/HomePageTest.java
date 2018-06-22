package controllers;

import controllers.fileSystem.FileSystem;
import org.junit.Before;
import org.junit.Test;
import request.HTTPRequest;
import response.HTTPResponse;

import static request.HTTPMethod.*;
import static org.junit.Assert.*;
import static response.StatusLine.OK;

public class HomePageTest {

    private HomePage homePage;
    private String root;

    @Before
    public void createInstance() {
        root = "src/test/java/root";
        homePage = new HomePage(new FileSystem(root));
    }

    @Test
    public void respondsWithOkToGetRequest() {
        HTTPResponse response = homePage.get(new HTTPRequest(GET.method, root));

        assertEquals(OK.message, response.getStatusLine());
    }

    @Test
    public void servesRootContentInBody() {
        HTTPResponse response = homePage.get(new HTTPRequest(GET.method, root));

        assertEquals("file.txt\nfile2.txt", response.getBody());
    }

    @Test
    public void respondsWithOkToHeadRequest() {
        HTTPResponse response = homePage.head(new HTTPRequest(HEAD.method, "/"));

        assertEquals(OK.message, response.getStatusLine());
    }
}
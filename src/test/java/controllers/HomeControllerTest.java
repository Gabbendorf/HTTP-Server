package controllers;

import controllers.fileSystem.FileSystem;
import org.junit.Before;
import org.junit.Test;
import request.HTTPRequest;
import response.HTTPResponse;

import static request.HTTPMethod.*;
import static org.junit.Assert.*;
import static response.StatusLine.OK;

public class HomeControllerTest {

    private HomeController homeController;
    private String root;

    @Before
    public void createInstance() {
        root = "src/test/java/root";
        homeController = new HomeController(new FileSystem(root));
    }

    @Test
    public void respondsWithOkToGetRequest() {
        HTTPResponse response = homeController.get(new HTTPRequest(GET.method, root));

        assertEquals(OK.message, response.getStatusLine());
    }

    @Test
    public void servesRootContentInBody() {
        HTTPResponse response = homeController.get(new HTTPRequest(GET.method, root));

        assertEquals("file.txt\nfile2.txt", response.getBody());
    }

    @Test
    public void respondsWithOkToHeadRequest() {
        HTTPResponse response = homeController.head(new HTTPRequest(HEAD.method, "/"));

        assertEquals(OK.message, response.getStatusLine());
    }
}
package controllers;

import controllers.fileSystem.FileSystem;
import org.junit.Before;
import org.junit.Test;
import request.HTTPPath;
import request.HTTPRequest;
import response.HTTPResponse;

import static request.HTTPMethod.*;
import static org.junit.Assert.*;
import static response.StatusLine.OK;

public class HomeControllerTest {

    private HomeController homeController;
    private String root;
    private HTTPPath rootPath;

    @Before
    public void createInstance() {
        root = "src/test/java/root";
        rootPath = new HTTPPath(root);
        homeController = new HomeController(new FileSystem(root));
    }

    @Test
    public void respondsWithOkToGetRequest() {
        HTTPResponse response = homeController.get(new HTTPRequest(GET.method, rootPath));

        assertEquals(OK.message, response.getStatusLine());
    }

    @Test
    public void servesRootContentInBody() {
        HTTPResponse response = homeController.get(new HTTPRequest(GET.method, rootPath));

        assertEquals("file.txt\nfile2.txt", response.getBody());
    }

    @Test
    public void respondsWithOkToHeadRequest() {
        HTTPResponse response = homeController.head(new HTTPRequest(HEAD.method, rootPath));

        assertEquals(OK.message, response.getStatusLine());
    }
}
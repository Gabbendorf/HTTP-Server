package controllers;

import org.junit.Before;
import org.junit.Test;
import request.HTTPRequest;
import response.HTTPResponse;

import static controllers.HTTPMethod.*;
import static org.junit.Assert.*;
import static response.StatusLine.OK;

public class HomePageTest {

    private HomePage homePage;

    @Before
    public void createInstance() {
        homePage = new HomePage();
    }

    @Test
    public void respondsWithOkToGetRequest() {
        HTTPResponse response = homePage.get(newRequest(GET));

        assertEquals(OK.statusLine, response.getStatusLine());
    }

    @Test
    public void respondsWithOkToHeadRequest() {
        HTTPResponse response = homePage.head(newRequest(HEAD));

        assertEquals(OK.statusLine, response.getStatusLine());
    }

    private HTTPRequest newRequest(HTTPMethod method) {
        return new HTTPRequest(method.method, "/");
    }
}
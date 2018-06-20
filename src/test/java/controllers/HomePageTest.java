package controllers;

import org.junit.Before;
import org.junit.Test;
import request.HTTPMethod;
import request.HTTPRequest;
import response.HTTPResponse;

import static request.HTTPMethod.*;
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

        assertEquals(OK.message, response.getStatusLine());
    }

    @Test
    public void respondsWithOkToHeadRequest() {
        HTTPResponse response = homePage.head(newRequest(HEAD));

        assertEquals(OK.message, response.getStatusLine());
    }

    private HTTPRequest newRequest(HTTPMethod method) {
        return new HTTPRequest(method.method, "/");
    }
}
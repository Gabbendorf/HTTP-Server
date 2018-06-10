package controllers;

import org.junit.Before;
import org.junit.Test;
import request.HTTPRequest;
import response.HTTPResponse;

import static controllers.HTTPMethod.GET;
import static controllers.HTTPMethod.HEAD;
import static controllers.HTTPMethod.OPTIONS;
import static org.junit.Assert.*;
import static response.StatusLine.OK;

public class MethodOptions2PageTest {

    private MethodOptions2Page methodOptions2page;

    @Before
    public void createInstance() {
        methodOptions2page = new MethodOptions2Page();
    }

    @Test
    public void respondsWithOkAndAllowHeaderToOptionsRequest() {
        HTTPResponse response = methodOptions2page.options(newRequest(OPTIONS));

        assertEquals(OK.statusLine, response.getStatusLine());
        assertEquals("Allow: GET,HEAD,OPTIONS", response.getHeaders());
    }

    @Test
    public void respondsWithOkToGetRequest() {
        HTTPResponse response = methodOptions2page.get(newRequest(GET));

        assertEquals(OK.statusLine, response.getStatusLine());
    }

    @Test
    public void respondsWithOkToHeadRequest() {
        HTTPResponse response = methodOptions2page.get(newRequest(HEAD));

        assertEquals(OK.statusLine, response.getStatusLine());
    }

    private HTTPRequest newRequest(HTTPMethod method) {
        return new HTTPRequest(method.method, "/");
    }
}
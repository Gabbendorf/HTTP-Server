package controllers;

import org.junit.Before;
import org.junit.Test;
import request.HTTPMethod;
import request.HTTPRequest;
import response.HTTPResponse;

import static request.HTTPMethod.GET;
import static request.HTTPMethod.HEAD;
import static request.HTTPMethod.OPTIONS;
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

        assertEquals(OK.message, response.getStatusLine());
        assertEquals("Allow: GET,HEAD,OPTIONS", response.getHeaders());
    }

    @Test
    public void respondsWithOkToGetRequest() {
        HTTPResponse response = methodOptions2page.get(newRequest(GET));

        assertEquals(OK.message, response.getStatusLine());
    }

    @Test
    public void respondsWithOkToHeadRequest() {
        HTTPResponse response = methodOptions2page.get(newRequest(HEAD));

        assertEquals(OK.message, response.getStatusLine());
    }

    private HTTPRequest newRequest(HTTPMethod method) {
        return new HTTPRequest(method.method, "/");
    }
}
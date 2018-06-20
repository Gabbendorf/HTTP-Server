package controllers;

import org.junit.Test;
import request.HTTPRequest;
import response.HTTPResponse;

import static request.HTTPMethod.OPTIONS;
import static org.junit.Assert.*;
import static response.StatusLine.OK;

public class MethodOptionsPageTest {

    @Test
    public void respondsWithOkAndAllowHeaderToOptionsRequest() {
        MethodOptionsPage methodOptionsPage = new MethodOptionsPage();

        HTTPResponse response = methodOptionsPage.options(new HTTPRequest(OPTIONS.method, "/"));

        assertEquals(OK.message, response.getStatusLine());
        assertEquals("Allow: GET,HEAD,POST,OPTIONS,PUT", response.getHeaders());
    }
}
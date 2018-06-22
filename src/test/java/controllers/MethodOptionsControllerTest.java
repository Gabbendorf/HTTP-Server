package controllers;

import org.junit.Test;
import request.HTTPRequest;
import response.HTTPResponse;

import static request.HTTPMethod.OPTIONS;
import static org.junit.Assert.*;
import static response.StatusLine.OK;

public class MethodOptionsControllerTest {

    @Test
    public void respondsWithOkAndAllowHeaderToOptionsRequest() {
        MethodOptionsController methodOptionsController = new MethodOptionsController();

        HTTPResponse response = methodOptionsController.options(new HTTPRequest(OPTIONS.method, "/"));

        assertEquals(OK.message, response.getStatusLine());
        assertEquals("Allow: GET,HEAD,POST,OPTIONS,PUT", response.getHeaders());
    }
}
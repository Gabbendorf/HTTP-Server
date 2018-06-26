package controllers;

import org.junit.Before;
import org.junit.Test;
import request.HTTPPath;
import request.HTTPRequest;
import response.HTTPResponse;

import static request.HTTPMethod.GET;
import static org.junit.Assert.*;
import static response.StatusLine.OK;
import static response.StatusLine.TEAPOT;

public class TeaControllerTest {

    private TeaController teaController;

    @Before
    public void createInstance() {
        teaController = new TeaController();
    }

    @Test
    public void respondsWithOkForTeaRequest() {
        HTTPResponse response = teaController.get(new HTTPRequest(GET.method, new HTTPPath("/tea")));

        assertEquals(OK.message, response.getStatusLine());
    }

    @Test
    public void respondsWith418AndBodyForCoffeeRequest() {
        HTTPResponse response = teaController.get(new HTTPRequest(GET.method, new HTTPPath("/coffee")));

        assertEquals(TEAPOT.message, response.getStatusLine());
        assertEquals("I'm a teapot", response.getBody());
    }
}
package controllers;

import org.junit.Before;
import org.junit.Test;
import request.HTTPRequest;
import response.HTTPResponse;

import static controllers.HTTPMethod.GET;
import static org.junit.Assert.*;
import static response.StatusLine.OK;
import static response.StatusLine.TEAPOT;

public class TeaPageTest {

    private TeaPage teaPage;

    @Before
    public void createInstance() {
        teaPage = new TeaPage();
    }

    @Test
    public void respondsWithOkForTeaRequest() {
        HTTPResponse response = teaPage.get(new HTTPRequest(GET.method, "/tea"));

        assertEquals(OK.message, response.getStatusLine());
    }

    @Test
    public void respondsWith418AndBodyForCoffeeRequest() {
        HTTPResponse response = teaPage.get(new HTTPRequest(GET.method, "/coffee"));

        assertEquals(TEAPOT.message, response.getStatusLine());
        assertEquals("I'm a teapot", response.getBody());
    }
}
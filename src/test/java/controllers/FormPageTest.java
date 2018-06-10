package controllers;

import org.junit.Test;
import request.HTTPRequest;
import response.HTTPResponse;

import static controllers.HTTPMethod.POST;
import static org.junit.Assert.*;
import static response.StatusLine.OK;

public class FormPageTest {

    @Test
    public void respondsWithOkToPostRequest() {
        FormPage formPage = new FormPage();

        HTTPResponse response = formPage.post(new HTTPRequest(POST.method, "/"));

        assertEquals(OK.statusLine, response.getStatusLine());
    }
}
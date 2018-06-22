package controllers;

import org.junit.Test;
import request.HTTPRequest;
import response.HTTPResponse;

import static request.HTTPMethod.POST;
import static org.junit.Assert.*;
import static response.StatusLine.OK;

public class FormControllerTest {

    @Test
    public void respondsWithOkToPostRequest() {
        FormController formController = new FormController();

        HTTPResponse response = formController.post(new HTTPRequest(POST.method, "/"));

        assertEquals(OK.message, response.getStatusLine());
    }
}
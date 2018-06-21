package controllers;

import org.junit.Test;
import request.HTTPRequest;
import response.HTTPResponse;

import static controllers.HTTPMethod.PUT;
import static org.junit.Assert.*;
import static response.StatusLine.OK;

public class PutTargetPageTest {

    @Test
    public void respondsWithOkToPutRequest() {
        PutTargetPage putTargetPage = new PutTargetPage();

        HTTPResponse response = putTargetPage.put(new HTTPRequest(PUT.method, "/"));

        assertEquals(OK.message, response.getStatusLine());
    }
}
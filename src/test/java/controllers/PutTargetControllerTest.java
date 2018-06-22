package controllers;

import org.junit.Test;
import request.HTTPRequest;
import response.HTTPResponse;

import static request.HTTPMethod.PUT;
import static org.junit.Assert.*;
import static response.StatusLine.OK;

public class PutTargetControllerTest {

    @Test
    public void respondsWithOkToPutRequest() {
        PutTargetController putTargetController = new PutTargetController();

        HTTPResponse response = putTargetController.put(new HTTPRequest(PUT.method, "/"));

        assertEquals(OK.message, response.getStatusLine());
    }
}
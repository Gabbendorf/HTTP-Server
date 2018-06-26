package controllers;

import org.junit.Before;
import org.junit.Test;
import request.HTTPMethod;
import request.HTTPPath;
import request.HTTPRequest;
import response.HTTPResponse;

import static request.HTTPMethod.*;
import static org.junit.Assert.*;
import static response.StatusLine.NOT_ALLOWED;

public class ControllerTest {

    private Controller controller;

    @Before
    public void createController() {
        controller = new Controller();
    }

    @Test
    public void allMethodsRespondWithNotAllowed() {
        assertTrue(isNotAllowed(controller.respondTo(newRequest(GET))));
        assertTrue(isNotAllowed(controller.respondTo(newRequest(PUT))));
        assertTrue(isNotAllowed(controller.respondTo(newRequest(HEAD))));
        assertTrue(isNotAllowed(controller.respondTo(newRequest(POST))));
        assertTrue(isNotAllowed(controller.respondTo(newRequest(OPTIONS))));
        assertTrue(isNotAllowed(controller.respondTo(newRequest(PATCH))));
        assertTrue(isNotAllowed(controller.respondTo(newRequest(INVALID))));
    }

    private boolean isNotAllowed(HTTPResponse response) {
        return response.getStatusLine().equals(NOT_ALLOWED.message);
    }

    private HTTPRequest newRequest(HTTPMethod method) {
        return new HTTPRequest(method.name(), new HTTPPath("/"));
    }
}
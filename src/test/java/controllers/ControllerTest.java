package controllers;

import org.junit.Before;
import org.junit.Test;
import request.HTTPRequest;
import response.HTTPResponse;

import static controllers.HTTPMethod.*;
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
        assertTrue(isNotAllowed(controller.respondTo(newRequest(INVALID))));
    }

    private boolean isNotAllowed(HTTPResponse response) {
        return response.getStatusLine().equals(NOT_ALLOWED.statusLine);
    }

    private HTTPRequest newRequest(HTTPMethod method) {
        return new HTTPRequest(method.name(), "/");
    }
}
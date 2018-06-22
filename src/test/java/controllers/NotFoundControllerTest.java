package controllers;

import org.junit.Before;
import org.junit.Test;
import request.HTTPMethod;
import request.HTTPRequest;
import response.HTTPResponse;

import java.util.ArrayList;
import java.util.List;

import static request.HTTPMethod.*;
import static org.junit.Assert.*;
import static response.StatusLine.NOT_FOUND;

public class NotFoundControllerTest {

    private NotFoundController notFoundController;
    private List<String> responses = new ArrayList<>();

    @Before
    public void createInstance() {
        notFoundController = new NotFoundController();
    }

    @Test
    public void respondsWithNotFoundForAllRequests() {
        addResponse(notFoundController.get(newRequest(GET, "/")));
        addResponse(notFoundController.post(newRequest(POST, "/")));
        addResponse(notFoundController.put(newRequest(PUT, "/")));
        addResponse(notFoundController.head(newRequest(HEAD, "/")));
        addResponse(notFoundController.patch(newRequest(PATCH, "/")));
        addResponse(notFoundController.options(newRequest(OPTIONS, "/")));

        assertTrue(areAllNotFoundResponses());
    }

    private HTTPRequest newRequest(HTTPMethod method, String path) {
        return new HTTPRequest(method.method, path);
    }

    private void addResponse(HTTPResponse response) {
        responses.add(response.getStatusLine());
    }

    private boolean areAllNotFoundResponses() {
        for (String response : responses) {
            if (response.equals(NOT_FOUND.message)) {
                return true;
            }
        }
        return false;
    }
}
package controllers;

import org.junit.Before;
import org.junit.Test;
import request.HTTPRequest;
import response.HTTPResponse;

import java.util.ArrayList;
import java.util.List;

import static controllers.HTTPMethod.*;
import static org.junit.Assert.*;
import static response.StatusLine.NOT_FOUND;

public class NotFoundPageTest {

    private NotFoundPage notFoundPage;
    private List<String> responses = new ArrayList<>();

    @Before
    public void createInstance() {
        notFoundPage = new NotFoundPage();
    }

    @Test
    public void respondsWithNotFoundForAllRequests() {
        addResponse(notFoundPage.get(newRequest(GET, "/")));
        addResponse(notFoundPage.post(newRequest(POST, "/")));
        addResponse(notFoundPage.put(newRequest(PUT, "/")));
        addResponse(notFoundPage.head(newRequest(HEAD, "/")));
        addResponse(notFoundPage.patch(newRequest(PATCH, "/")));
        addResponse(notFoundPage.options(newRequest(OPTIONS, "/")));

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
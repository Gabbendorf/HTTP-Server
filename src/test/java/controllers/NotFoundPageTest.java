package controllers;

import org.junit.Before;
import org.junit.Test;
import request.HTTPRequest;
import response.HTTPResponse;

import static controllers.HTTPMethod.GET;
import static controllers.HTTPMethod.INVALID;
import static org.junit.Assert.*;
import static response.StatusLine.NOT_FOUND;

public class NotFoundPageTest {

    private NotFoundPage notFoundPage;

    @Before
    public void createInstance() {
        notFoundPage = new NotFoundPage();
    }

    @Test
    public void respondsWithNotFoundForInvalidMethod() {
        HTTPResponse response = notFoundPage.respondTo(new HTTPRequest(INVALID.method, "/"));

        assertEquals(NOT_FOUND.statusLine, response.getStatusLine());
    }

    @Test
    public void respondsWithNotFoundForNotExistingPath() {
        HTTPResponse response = notFoundPage.respondTo(new HTTPRequest(GET.method, "/not-existing"));

        assertEquals(NOT_FOUND.statusLine, response.getStatusLine());
    }
}
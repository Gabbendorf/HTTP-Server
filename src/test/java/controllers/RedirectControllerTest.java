package controllers;

import org.junit.Test;
import request.HTTPRequest;
import response.HTTPResponse;

import static request.HTTPMethod.GET;
import static org.junit.Assert.*;
import static response.StatusLine.FOUND;

public class RedirectControllerTest {

    @Test
    public void respondsWithFoundAndLocationHeaderToGetRequest() {
        RedirectController redirectController = new RedirectController();

        HTTPResponse response = redirectController.get(new HTTPRequest(GET.method, "/"));

        assertEquals(FOUND.message, response.getStatusLine());
        assertEquals("Location: /", response.getHeaders());
    }
}
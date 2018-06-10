package controllers;

import org.junit.Test;
import request.HTTPRequest;
import response.HTTPResponse;

import static controllers.HTTPMethod.GET;
import static org.junit.Assert.*;
import static response.StatusLine.FOUND;

public class RedirectPageTest {

    @Test
    public void respondsWithFoundAndLocationHeaderToGetRequest() {
        RedirectPage redirectPage = new RedirectPage();

        HTTPResponse response = redirectPage.get(new HTTPRequest(GET.method, "/"));

        assertEquals(FOUND.statusLine, response.getStatusLine());
        assertEquals("Location: /", response.getHeaders());
    }
}
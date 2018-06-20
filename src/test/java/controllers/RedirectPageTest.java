package controllers;

import org.junit.Test;
import request.HTTPRequest;
import response.HTTPResponse;

import static request.HTTPMethod.GET;
import static org.junit.Assert.*;
import static response.StatusLine.FOUND;

public class RedirectPageTest {

    @Test
    public void respondsWithFoundAndLocationHeaderToGetRequest() {
        RedirectPage redirectPage = new RedirectPage();

        HTTPResponse response = redirectPage.get(new HTTPRequest(GET.method, "/"));

        assertEquals(FOUND.message, response.getStatusLine());
        assertEquals("Location: /", response.getHeaders());
    }
}
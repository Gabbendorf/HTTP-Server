package controllers;

import org.junit.Before;
import org.junit.Test;
import request.HTTPRequest;
import response.HTTPResponse;

import java.util.LinkedHashMap;
import java.util.Map;

import static jdk.nashorn.internal.runtime.PropertyDescriptor.GET;
import static org.junit.Assert.*;
import static response.StatusLine.OK;

public class CookiePageTest {

    private CookiePage cookiePage;

    @Before
    public void createInstance() {
        cookiePage = new CookiePage();
    }

    @Test
    public void respondsWithOkSetCookeAndBodyForNoCookieRequest() {
        HTTPResponse response = cookiePage.get(new HTTPRequest(GET, "/cookie?type=chocolate", headers("")));

        assertEquals(OK.message, response.getStatusLine());
        assertEquals("Set-Cookie: type=chocolate", response.getHeaders());
        assertEquals("Eat", response.getBody());
    }

    @Test
    public void respondsWithOkAndBodyForCookieRequest() {
        HTTPResponse response = cookiePage.get(new HTTPRequest(GET, "/eat_cookie", headers("Cookie")));

        assertEquals(OK.message, response.getStatusLine());
        assertEquals("mmmm chocolate", response.getBody());
    }

    private Map<String, String> headers(String cookie) {
        Map<String, String> headers = new LinkedHashMap<>();
        headers.put(cookie, "type=chocolate");
        return headers;
    }
}
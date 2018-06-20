package controllers;

import org.junit.Before;
import org.junit.Test;
import request.HTTPRequest;
import response.HTTPResponse;
import router.Logger;

import java.util.LinkedHashMap;
import java.util.Map;

import static controllers.HTTPMethod.GET;
import static org.junit.Assert.*;
import static response.StatusLine.OK;
import static response.StatusLine.UNAUTHORIZED;

public class LogsPageTest {

    private LogsPage logsPage;

    @Before
    public void createInstance() {
        logsPage = new LogsPage(new Logger());
    }

    @Test
    public void respondsWithUnauthorizedStatusCodeForMissingAuthorizationHeader() {
        HTTPResponse response = logsPage.get(new HTTPRequest(GET.method, "/logs", headers("Localhost", "")));

        assertEquals(UNAUTHORIZED.message, response.getStatusLine());
    }

    @Test
    public void respondsWithWWWAuthenticateHeaderForMissingAuthorisationHeader() {
        HTTPResponse response = logsPage.get(new HTTPRequest(GET.method, "/logs", headers("Localhost", "")));

        assertEquals("WWW-Authenticate: Basic realm=\"AccessToTheLogs\"", response.getHeaders());
    }

    @Test
    public void respondsWithOkForAuthorizedRequest() {
        HTTPResponse response = logsPage.get(new HTTPRequest(GET.method, "/logs", headers("Authorization", "Basic YWRtaW46aHVudGVyMg==")));

        assertEquals(OK.message, response.getStatusLine());
    }

    private Map<String, String> headers(String key, String value) {
        Map<String, String> headers = new LinkedHashMap<>();
        headers.put(key, value);
        return headers;
    }
}
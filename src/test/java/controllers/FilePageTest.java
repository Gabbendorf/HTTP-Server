package controllers;

import org.junit.Test;
import request.HTTPRequest;
import response.HTTPResponse;

import static controllers.HTTPMethod.GET;
import static org.junit.Assert.*;
import static response.StatusLine.OK;

public class FilePageTest {

    @Test
    public void respondsWithOkToGetRequest() {
        FilePage filePage = new FilePage();

        HTTPResponse response = filePage.get(new HTTPRequest(GET.method, "/file1"));

        assertEquals(OK.statusLine, response.getStatusLine());
    }
}
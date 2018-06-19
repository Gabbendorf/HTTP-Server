package controllers;

import exceptions.NotSupportedEncodingException;
import org.junit.Test;
import request.HTTPRequest;
import response.HTTPResponse;

import java.io.UnsupportedEncodingException;

import static controllers.HTTPMethod.GET;
import static org.junit.Assert.*;

public class ParameterPageTest {

    @Test
    public void respondsWithDecodedQueryStringInBody() {
        ParameterPage parameterPage = new ParameterPage();
        String pathWithQueryString = "/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%" +
                "26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff";

        HTTPResponse response = parameterPage.get(new HTTPRequest(GET.method, pathWithQueryString));

        assertEquals("variable_1 = Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?" +
                "\nvariable_2 = stuff\n", response.getBody());
    }

    @Test(expected = NotSupportedEncodingException.class)
    public void throwsNotSupportedEncodingException() {
        ParameterPageWithNotSupportedEncodingException parameterPage = new ParameterPageWithNotSupportedEncodingException();

        parameterPage.get(new HTTPRequest(GET.method, "/"));
    }
}

class ParameterPageWithNotSupportedEncodingException extends ParameterPage {

    @Override
    public HTTPResponse get(HTTPRequest request) {
        throw new NotSupportedEncodingException(new UnsupportedEncodingException());
    }
}
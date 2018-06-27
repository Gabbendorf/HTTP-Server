package request;

import org.junit.Test;

import static org.junit.Assert.*;
import static request.HTTPMethod.*;

public class HTTPMethodTest {

    @Test
    public void createsGet() {
        HTTPMethod method = HTTPMethod.create("GET");

        assertEquals(GET, method);
    }

    @Test
    public void createsOptions() {
        HTTPMethod method = HTTPMethod.create("OPTIONS");

        assertEquals(OPTIONS, method);
    }

    @Test
    public void createsPut() {
        HTTPMethod method = HTTPMethod.create("PUT");

        assertEquals(PUT, method);
    }

    @Test
    public void createsPost() {
        HTTPMethod method = HTTPMethod.create("POST");

        assertEquals(POST, method);
    }

    @Test
    public void createsHead() {
        HTTPMethod method = HTTPMethod.create("HEAD");

        assertEquals(HEAD, method);
    }

    @Test
    public void createsPatch() {
        HTTPMethod method = HTTPMethod.create("PATCH");

        assertEquals(PATCH, method);
    }

    @Test
    public void createsDelete() {
        HTTPMethod method = HTTPMethod.create("DELETE");

        assertEquals(DELETE, method);
    }

    @Test
    public void createsInvalid() {
        HTTPMethod method = HTTPMethod.create("FHIWVIW");

        assertEquals(INVALID, method);
    }
}
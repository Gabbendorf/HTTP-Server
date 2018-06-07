package request;

import exceptions.InputStreamException;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class RequestReaderTest {

    @Test
    public void readsRequestUntilEndOfHeaders() {
        String rawRequest = "GET / HTTP/1.1\nheader: something\nheader2: other\n\nciao";
        RequestReader requestReader = new RequestReader(inputStream(rawRequest));

        HTTPRequest request = requestReader.readRequest();

        assertEquals("GET / HTTP/1.1", request.getRequestLine());
        assertFalse(request.getHeaders().containsKey("ciao"));
    }

    @Test(expected = InputStreamException.class)
    public void throwsInputStreamException() {
        RequestReaderWithException requestReader = new RequestReaderWithException(inputStream("GET / HTTP/1.1"));

        requestReader.readRequest();
    }

    private InputStream inputStream(String request) {
        return new ByteArrayInputStream(request.getBytes());
    }

    class RequestReaderWithException extends RequestReader {

        public RequestReaderWithException(InputStream inputStream) {
            super(inputStream);
        }

        @Override
        public HTTPRequest readRequest() {
            throw new InputStreamException(new IOException());
        }
    }

}
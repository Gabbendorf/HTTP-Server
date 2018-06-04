package request;

import exceptions.InputStreamException;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class RequestReaderTest {

    @Test
    public void readsRequestUntilFindsEmptyLine() {
        RequestReader requestReader = new RequestReader(inputStream("GET / HTTP/1.1\nhi\n\nciao"));

        HTTPRequest request = requestReader.readRequest();

        assertEquals("GET / HTTP/1.1hi", request.getRequestLine());
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

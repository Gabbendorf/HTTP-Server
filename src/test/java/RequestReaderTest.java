import exceptions.InputStreamException;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class RequestReaderTest {

    @Test
    public void readsRequest() {
        RequestReader requestReader = new RequestReader(inputStream("GET / HTTP/1.1\n"));

        String request = requestReader.readRequest();

        assertEquals("GET / HTTP/1.1", request);
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
        public String readRequest() {
            throw new InputStreamException(new IOException());
        }
    }

}

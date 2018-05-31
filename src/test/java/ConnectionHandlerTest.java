import org.junit.Before;
import org.junit.Test;
import request.HTTPRequest;
import request.RequestReader;
import response.ResponseWriter;

import java.io.*;

import static org.junit.Assert.assertTrue;

public class ConnectionHandlerTest {

    private ConnectionHandler connectionHandler;
    private RequestReaderSpy requestReader;
    private ResponseWriterSpy responseWriter;

    @Before
    public void createInstances() {
        requestReader = new RequestReaderSpy(new ByteArrayInputStream("".getBytes()));
        responseWriter = new ResponseWriterSpy(new ByteArrayOutputStream());
        connectionHandler = new ConnectionHandler(requestReader, responseWriter);
    }

    @Test
    public void readsRequest() {
        connectionHandler.run();

        assertTrue(requestReader.hasRead);
    }

    @Test
    public void writesResponse() {
        connectionHandler.run();

        assertTrue(responseWriter.hasWritten);
    }

    class RequestReaderSpy extends RequestReader {

        public boolean hasRead = false;

        public RequestReaderSpy(InputStream inputStream) {
            super(inputStream);
        }

        @Override
        public HTTPRequest readRequest() {
            hasRead = true;
            return null;
        }
    }

    class ResponseWriterSpy extends ResponseWriter {

        public boolean hasWritten = false;

        public ResponseWriterSpy(OutputStream outputStream) {
            super(outputStream);
        }

        @Override
        public void write(String response) {
            hasWritten = true;
        }
    }
}

package server;

import org.junit.Before;
import org.junit.Test;
import request.HTTPRequest;
import request.RequestReader;
import response.ResponseWriter;
import router.Logger;

import java.io.*;

import static org.junit.Assert.assertTrue;

public class ConnectionHandlerTest {

    private ConnectionHandler connectionHandler;
    private RequestReaderSpy requestReader;
    private ResponseWriterSpy responseWriter;
    private Logger logger;

    @Before
    public void createInstances() {
        requestReader = new RequestReaderSpy(new ByteArrayInputStream("".getBytes()));
        responseWriter = new ResponseWriterSpy(new ByteArrayOutputStream(), new SocketStub());
        logger = new Logger();
        connectionHandler = new ConnectionHandler(requestReader, responseWriter, logger, "");
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

    @Test(expected = RuntimeException.class)
    public void throwsRunTimeException() {
        ConnectionHandlerWithRunTimeException connectionHandler = new ConnectionHandlerWithRunTimeException(requestReader, responseWriter, logger, "");

        connectionHandler.run();
    }

    class RequestReaderSpy extends RequestReader {

        public boolean hasRead = false;

        public RequestReaderSpy(InputStream inputStream) {
            super(inputStream);
        }

        @Override
        public HTTPRequest readRequest() {
            hasRead = true;
            return new HTTPRequest("", "");
        }
    }

    class ResponseWriterSpy extends ResponseWriter {

        public boolean hasWritten = false;

        public ResponseWriterSpy(OutputStream outputStream, SocketStub socketStub) {
            super(outputStream, socketStub);
        }

        @Override
        public void write(String response) {
            hasWritten = true;
        }
    }

    class ConnectionHandlerWithRunTimeException extends ConnectionHandler {

        public ConnectionHandlerWithRunTimeException(RequestReader requestReader, ResponseWriter responseWriter, Logger logger, String directory) {
            super(requestReader, responseWriter, logger, directory);
        }

        @Override
        public void run() {
            throw new RuntimeException();
        }
    }
}

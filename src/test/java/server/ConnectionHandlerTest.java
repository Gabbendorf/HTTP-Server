package server;

import exceptions.SocketClosureException;
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
    private SocketStub socketStub;
    private Logger logger;

    @Before
    public void createInstances() {
        requestReader = new RequestReaderSpy(new ByteArrayInputStream("".getBytes()));
        responseWriter = new ResponseWriterSpy(new ByteArrayOutputStream());
        socketStub = new SocketStub();
        logger = new Logger();
        connectionHandler = new ConnectionHandler(requestReader, responseWriter, socketStub, logger);
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

    @Test
    public void closesSocket() {
        connectionHandler.run();

        assertTrue(socketStub.isClosed);
    }

    @Test(expected = SocketClosureException.class)
    public void throwsSocketClosureException() {
        ConnectionHandlerWithException connectionHandler = new ConnectionHandlerWithException(requestReader, responseWriter, socketStub, logger);

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

        public ResponseWriterSpy(OutputStream outputStream) {
            super(outputStream);
        }

        @Override
        public void write(String response) {
            hasWritten = true;
        }
    }

    class ConnectionHandlerWithException extends ConnectionHandler {

        public ConnectionHandlerWithException(RequestReader requestReader, ResponseWriter responseWriter, Closeable socket, Logger logger) {
            super(requestReader, responseWriter, socket, logger);
        }

        @Override
        public void run() {
            throw new SocketClosureException(new IOException());
        }
    }
}

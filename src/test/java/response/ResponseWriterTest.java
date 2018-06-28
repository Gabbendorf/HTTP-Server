package response;

import exceptions.SocketClosureException;
import org.junit.Before;
import org.junit.Test;
import server.SocketStub;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ResponseWriterTest {

    private ResponseWriter writer;
    private ByteArrayOutputStream outputStream;
    private SocketStub socketStub;

    @Before
    public void createInstances() {
        outputStream = new ByteArrayOutputStream();
        socketStub = new SocketStub();
        writer = new ResponseWriter(outputStream, socketStub);
    }

    @Test
    public void writesResponseToStream() {
        writer.write("HTTP/1.1 200 OK");

        assertEquals("HTTP/1.1 200 OK\n", outputStream.toString());
    }

    @Test
    public void closesSocket() {
        writer.write("hi");

        assertTrue(socketStub.isClosed);
    }


    @Test(expected = SocketClosureException.class)
    public void throwsSocketClosureException() {
        ResponseWriterWithSocketClosureException responseWriterWithSocketClosureException = new ResponseWriterWithSocketClosureException(outputStream, socketStub);

        responseWriterWithSocketClosureException.write("hi");
    }
}

class ResponseWriterWithSocketClosureException extends ResponseWriter {

    public ResponseWriterWithSocketClosureException(OutputStream outputStream, Closeable socket) {
        super(outputStream, socket);
    }

    public void write(String response) {
        throw new SocketClosureException(new IOException());
    }
}

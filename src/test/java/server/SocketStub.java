package server;

import java.io.*;
import java.net.Socket;

public class SocketStub extends Socket {

    public boolean isClosed = false;

    @Override
    public InputStream getInputStream() {
        return new ByteArrayInputStream("".getBytes());
    }

    @Override
    public OutputStream getOutputStream() {
        return new ByteArrayOutputStream();
    }

    @Override
    public void close() {
        isClosed = true;
    }
}

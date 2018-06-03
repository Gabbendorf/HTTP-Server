package server;

import exceptions.SocketClosureException;
import request.RequestReader;
import response.ResponseWriter;

import java.io.Closeable;
import java.io.IOException;

public class ConnectionHandler implements Runnable {

    private final RequestReader requestReader;
    private final ResponseWriter responseWriter;
    private final Closeable socket;

    public ConnectionHandler(RequestReader requestReader, ResponseWriter responseWriter, Closeable socket) {
        this.requestReader = requestReader;
        this.responseWriter = responseWriter;
        this.socket = socket;
    }

    @Override
    public void run() {
        requestReader.readRequest();
        responseWriter.write("HTTP/1.1 200 OK");
        try {
            socket.close();
        } catch (IOException e) {
            throw new SocketClosureException(e);
        }
    }
}

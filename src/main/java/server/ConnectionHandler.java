package server;

import exceptions.SocketClosureException;
import request.HTTPRequest;
import request.RequestReader;
import response.HTTPResponse;
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
        HTTPResponse response = new HTTPResponse("200", "OK");
        responseWriter.write(response.statusLine());
        try {
            socket.close();
        } catch (IOException e) {
            throw new SocketClosureException(e);
        }
    }
}

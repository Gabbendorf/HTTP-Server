package server;

import exceptions.SocketClosureException;
import request.HTTPRequest;
import request.RequestReader;
import response.HTTPResponse;
import response.ResponseWriter;

import java.io.Closeable;
import java.io.IOException;

import static response.StatusLine.NOT_FOUND;
import static response.StatusLine.OK;

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
        HTTPRequest request = requestReader.readRequest();
        HTTPResponse response = new HTTPResponse(route(request));
        responseWriter.write(response.statusLine());
        try {
            socket.close();
        } catch (IOException e) {
            throw new SocketClosureException(e);
        }
    }

    private String route(HTTPRequest request) {
        if (!request.getUrl().equals("/") && (request.getMethod().equals("HEAD"))) {
            return NOT_FOUND.statusLine;
        } else {
            return OK.statusLine;
        }
    }
}

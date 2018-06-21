package server;

import exceptions.SocketClosureException;
import request.HTTPRequest;
import request.RequestReader;
import response.HTTPResponse;
import response.ResponseWriter;
import router.Logger;
import router.Router;

import java.io.Closeable;
import java.io.IOException;

import static response.StatusLine.INTERNAL_SERVER_ERROR;

public class ConnectionHandler implements Runnable {

    private final RequestReader requestReader;
    private final ResponseWriter responseWriter;
    private final Closeable socket;
    private final Router router;

    public ConnectionHandler(RequestReader requestReader, ResponseWriter responseWriter, Closeable socket, Logger logger, String root) {
        this.requestReader = requestReader;
        this.responseWriter = responseWriter;
        this.socket = socket;
        this.router = new Router(logger, root);
    }

    @Override
    public void run() {
        try {
            HTTPRequest request = requestReader.readRequest();
            HTTPResponse response = router.route(request);
            responseWriter.write(response.getResponse());
            closeSocket();
        } catch (Exception e) {
            try {
                internalServerErrorResponse(e);
            } catch (IOException e1) {
                throw new RuntimeException(e1.getMessage(), e1);
            }
        }
    }

    private void closeSocket() {
        try {
            socket.close();
        } catch (IOException e) {
            throw new SocketClosureException(e);
        }
    }

    private void internalServerErrorResponse(Exception e) throws IOException {
        HTTPResponse response = new HTTPResponse(INTERNAL_SERVER_ERROR, e.getMessage());
        responseWriter.write(response.getResponse());
        closeSocket();
    }
}

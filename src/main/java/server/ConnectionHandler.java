package server;

import exceptions.SocketClosureException;
import request.HTTPRequest;
import request.RequestReader;
import response.HTTPResponse;
import response.ResponseWriter;
import router.Router;

import java.io.Closeable;
import java.io.IOException;

public class ConnectionHandler implements Runnable {

    private final RequestReader requestReader;
    private final ResponseWriter responseWriter;
    private final Closeable socket;
    private final Router router;

    public ConnectionHandler(RequestReader requestReader, ResponseWriter responseWriter, Closeable socket) {
        this.requestReader = requestReader;
        this.responseWriter = responseWriter;
        this.socket = socket;
        this.router = new Router();
    }

    @Override
    public void run() {
        HTTPRequest request = requestReader.readRequest();
        HTTPResponse response = router.route(request);
        responseWriter.write(response.getResponse());
        try {
            socket.close();
        } catch (IOException e) {
            throw new SocketClosureException(e);
        }
    }
}

package server;

import controllers.fileSystem.FileSystem;
import request.HTTPRequest;
import request.RequestReader;
import response.HTTPResponse;
import response.ResponseWriter;
import router.Logger;
import router.Router;

import java.io.IOException;

import static response.StatusLine.INTERNAL_SERVER_ERROR;

public class ConnectionHandler implements Runnable {

    private final RequestReader requestReader;
    private final ResponseWriter responseWriter;
    private final Router router;

    public ConnectionHandler(RequestReader requestReader, ResponseWriter responseWriter, Logger logger, FileSystem fileSystem) {
        this.requestReader = requestReader;
        this.responseWriter = responseWriter;
        this.router = new Router(logger, fileSystem);
    }

    @Override
    public void run() {
        try {
            HTTPRequest request = requestReader.readRequest();
            HTTPResponse response = router.route(request);
            responseWriter.write(response.getResponse());
        } catch (Exception e) {
            try {
                internalServerErrorResponse(e);
            } catch (IOException e1) {
                throw new RuntimeException(e1.getMessage(), e1);
            }
        }
    }

    private void internalServerErrorResponse(Exception e) throws IOException {
        HTTPResponse response = new HTTPResponse(INTERNAL_SERVER_ERROR, e.getMessage());
        responseWriter.write(response.getResponse());
    }
}

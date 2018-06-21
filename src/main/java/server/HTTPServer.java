package server;

import exceptions.ConnectionException;
import request.RequestReader;
import response.ResponseWriter;
import router.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HTTPServer {

    private final ConnectionsExecutor executor;
    private Logger logger;
    private String directory;
    private final ServerSocket serverSocket;

    public HTTPServer(ServerSocket serverSocket, ConnectionsExecutor executor, Logger logger, String directory) {
        this.serverSocket = serverSocket;
        this.executor = executor;
        this.logger = logger;
        this.directory = directory;
    }

    public void start(ServerStatus serverStatus) {
        while (serverStatus.isRunning()) {
            try {
                Socket connectedSocket = serverSocket.accept();
                RequestReader requestReader = new RequestReader(connectedSocket.getInputStream());
                ResponseWriter responseWriter = new ResponseWriter(connectedSocket.getOutputStream());
                executor.execute(new ConnectionHandler(requestReader, responseWriter, connectedSocket, logger, directory));
            } catch (IOException e) {
                throw new ConnectionException(e);
            }
        }
    }
}

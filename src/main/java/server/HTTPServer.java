package server;

import request.RequestReader;
import response.ResponseWriter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HTTPServer {

    private final ConnectionsExecutor executor;
    private final ServerSocket serverSocket;

    public HTTPServer(ServerSocket serverSocket, ConnectionsExecutor executor) {
        this.serverSocket = serverSocket;
        this.executor = executor;
    }

    public void start(ServerStatus serverStatus) {
        try {
            while (serverStatus.isRunning()) {
                Socket connectedSocket = serverSocket.accept();
                RequestReader requestReader = new RequestReader(connectedSocket.getInputStream());
                ResponseWriter responseWriter = new ResponseWriter(connectedSocket.getOutputStream());
                executor.execute(new ConnectionHandler(requestReader, responseWriter, connectedSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

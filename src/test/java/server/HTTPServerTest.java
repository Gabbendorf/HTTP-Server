package server;

import controllers.fileSystem.FileSystem;
import exceptions.ConnectionException;
import org.junit.Before;
import org.junit.Test;
import router.Logger;

import java.io.*;
import java.net.ServerSocket;

import static org.junit.Assert.assertTrue;

public class HTTPServerTest {

    private ExecutorSpy executorSpy;
    private ServerSocketStub serverSocketStub;
    private Logger logger;

    @Before
    public void createInstances() throws IOException {
        executorSpy = new ExecutorSpy();
        serverSocketStub = new ServerSocketStub();
        logger = new Logger();
    }

    @Test
    public void executesMultipleConnections() {
        HTTPServer server = new HTTPServer(serverSocketStub, executorSpy, logger, new FileSystem("/"));

        server.start(new ServerStatusStub());

        assertTrue(executorSpy.executeWasCalledWith(executorSpy.connectionHandler));
    }

    @Test(expected = ConnectionException.class)
    public void throwsConnectionException() {
        HTTPServerWithException httpServerWithException = new HTTPServerWithException(serverSocketStub, executorSpy, logger, new FileSystem("/"));

        httpServerWithException.start(new ServerStatusStub());
    }

    class ExecutorSpy implements ConnectionsExecutor {

        public Object connectionHandler;

        @Override
        public void execute(ConnectionHandler connectionHandler) {
            this.connectionHandler = connectionHandler;
        }

        boolean executeWasCalledWith(Object argument) {
            return argument.equals(connectionHandler);
        }
    }

    class ServerStatusStub extends ServerStatus {

        private int connectionsMade = 0;

        @Override
        public boolean isRunning() {
            while (connectionsMade < 11) {
                connectionsMade += 1;
                return true;
            }
            return false;
        }
    }

    class HTTPServerWithException extends HTTPServer {

        public HTTPServerWithException(ServerSocket serverSocket, ConnectionsExecutor executor, Logger logger, FileSystem fileSystem) {
            super(serverSocket, executor, logger, fileSystem);
        }

        @Override
        public void start(ServerStatus serverStatus) {
            throw new ConnectionException(new IOException());
        }
    }
}

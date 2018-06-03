package server;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertTrue;

public class HTTPServerTest {

    @Test
    public void executesMultipleConnections() throws IOException {
        ExecutorSpy executorSpy = new ExecutorSpy();
        HTTPServer server = new HTTPServer(new ServerSocketStub(), executorSpy);

        server.start(new ServerStatusStub());

        assertTrue(executorSpy.executeWasCalledWith(executorSpy.connectionHandler));
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
}

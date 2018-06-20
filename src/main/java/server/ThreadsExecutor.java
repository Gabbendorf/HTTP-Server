package server;

import java.util.concurrent.ExecutorService;

public class ThreadsExecutor implements ConnectionsExecutor {

    private final ExecutorService threadPool;

    public ThreadsExecutor(ExecutorService threadPool) {
        this.threadPool = threadPool;
    }

    @Override
    public void execute(ConnectionHandler connectionHandler) {
        threadPool.execute(connectionHandler);
    }
}

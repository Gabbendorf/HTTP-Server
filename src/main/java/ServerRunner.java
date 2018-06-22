import controllers.fileSystem.FileSystem;
import router.Logger;
import server.ThreadsExecutor;
import server.HTTPServer;
import server.ServerStatus;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerRunner {

    private static ExecutorService threadPool = Executors.newFixedThreadPool(20);

    public static void main(String[] args) {
        int port = Integer.parseInt(args[1]);
        String root = args[3];
        try {
            HTTPServer server = new HTTPServer(new ServerSocket(port),
                                               new ThreadsExecutor(threadPool),
                                               new Logger(),
                                               new FileSystem(root));

            server.start(new ServerStatus());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}

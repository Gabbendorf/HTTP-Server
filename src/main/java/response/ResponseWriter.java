package response;

import exceptions.SocketClosureException;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class ResponseWriter {

    private final PrintWriter printWriter;
    private Closeable socket;

    public ResponseWriter(OutputStream outputStream, Closeable socket) {
        this.printWriter = new PrintWriter(outputStream, true);
        this.socket = socket;
    }

    public void write(String response) {
        printWriter.println(response);
        try {
            socket.close();
        } catch (IOException e) {
            throw new SocketClosureException(e);
        }
    }
}

import request.RequestReader;
import response.ResponseWriter;

public class ConnectionHandler implements Runnable {

    private final RequestReader requestReader;
    private final ResponseWriter responseWriter;

    public ConnectionHandler(RequestReader requestReader, ResponseWriter responseWriter) {
        this.requestReader = requestReader;
        this.responseWriter = responseWriter;
    }

    @Override
    public void run() {
        requestReader.readRequest();
        responseWriter.write("HTTP/1.1 200 OK");
    }
}

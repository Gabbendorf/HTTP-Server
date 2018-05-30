package response;

import java.io.OutputStream;
import java.io.PrintWriter;

public class ResponseWriter {

    private final PrintWriter printWriter;

    public ResponseWriter(OutputStream outputStream) {
       this.printWriter = new PrintWriter(outputStream, true);
    }

    public void write(String response) {
       printWriter.println(response);
    }
}

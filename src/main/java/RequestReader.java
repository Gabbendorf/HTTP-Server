import exceptions.InputStreamException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RequestReader {

    private final BufferedReader bufferedReader;

    public RequestReader(InputStream inputStream) {
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }

    public HTTPRequest readRequest() {
        StringBuilder request = new StringBuilder();
        String line;
        try {
            line = bufferedReader.readLine();
            while (line != null) {
                request.append(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new InputStreamException(e);
        }
        return new HTTPRequest(new RequestParser(request.toString()));
    }
}

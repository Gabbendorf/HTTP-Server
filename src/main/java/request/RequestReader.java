package request;

import exceptions.InputStreamException;

import java.io.*;

public class RequestReader {

    private final BufferedReader bufferedReader;

    public RequestReader(InputStream inputStream) {
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }


    public HTTPRequest readRequest() {
        StringBuilder requestLine = new StringBuilder();
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                if (isEndOfHeaders(line)) {
                    break;
                }
                requestLine.append(line);
            }
        } catch (IOException e) {
            throw new InputStreamException(e);
        }
        return new HTTPRequest(new RequestParser(requestLine.toString()));
    }

    private boolean isEndOfHeaders(String line) {
        return line.equals("");
    }
}

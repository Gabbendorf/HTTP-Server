package request;

import exceptions.InputStreamException;

import java.io.*;
import java.util.*;

public class RequestReader {

    private final BufferedReader bufferedReader;
    private final RequestParser parser;

    public RequestReader(InputStream inputStream) {
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        this.parser = new RequestParser();
    }

    public HTTPRequest readRequest() {
        String statusLine = readLine();
        List<String> headers = new ArrayList<>();
        String body = "";
        String line;
        while ((line = readLine()) != null) {
            if (isEndOfHeaders(line)) {
                body += readLine();
                break;
            }
            headers.add(line);
        }
        return new HTTPRequest(parser.method(statusLine),
                               parser.path(statusLine),
                               parser.headers(headers),
                               body);
    }

    private String readLine() {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            throw new InputStreamException(e);
        }
    }

    private boolean isEndOfHeaders(String line) {
        return line.equals("");
    }
}

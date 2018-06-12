package request;

import exceptions.InputStreamException;

import java.io.*;
import java.util.*;

public class RequestReader {

    private final BufferedReader bufferedReader;

    public RequestReader(InputStream inputStream) {
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }

    public HTTPRequest readRequest() {
        List<String> requestToHeaders = new ArrayList<>();
        String body = "";
        String line;
        while ((line = readLine()) != null) {
            if (isEndOfHeaders(line)) {
                body += readLine();
                break;
            }
            requestToHeaders.add(line);
        }
        RequestParser parser = new RequestParser(requestToHeaders);
        return new HTTPRequest(parser.method(), parser.path(), parser.headers(), body);
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

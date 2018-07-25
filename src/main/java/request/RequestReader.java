package request;

import exceptions.InputStreamException;

import java.io.*;
import java.nio.CharBuffer;
import java.util.*;

public class RequestReader {

    private final BufferedReader bufferedReader;
    private final RequestParser parser;
    private final static String CONTENT_LENGTH = "Content-Length";

    public RequestReader(InputStream inputStream) {
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        this.parser = new RequestParser();
    }

    public HTTPRequest readRequest() {
        String requestLine = readLine();
        List<String> headers = new ArrayList<>();
        StringBuilder body = new StringBuilder();
        String line;
        while ((line = readLine()) != null) {
            if (isEndOfHeaders(line)) {
                body.append(readBody(headers));
                break;
            }
            headers.add(line);
        }
        return new HTTPRequest(parser.method(requestLine),
                               parser.path(requestLine),
                               parser.headers(headers),
                               body.toString());
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

    private String readBody(List<String> headers) {
        StringBuilder body = new StringBuilder();
        if (itHasBody(headers))
            try {
                CharBuffer buffer = CharBuffer.allocate(contentLength(headers));
                bufferedReader.read(buffer);
                buffer.flip();
                body.append(buffer);
            } catch (IOException e) {
                throw new InputStreamException(e);
            }
        else {
            body.append("");
        }
        return body.toString();
    }

    private int contentLength(List<String> headers) {
        return Integer.parseInt(parser.headers(headers).get(CONTENT_LENGTH).trim());
    }

    private boolean itHasBody(List<String> headers) {
        return parser.headers(headers).containsKey(CONTENT_LENGTH);
    }
}

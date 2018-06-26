package controllers.fileSystem;

import controllers.Controller;
import request.HTTPRequest;
import response.HTTPResponse;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static response.StatusLine.*;

public class FilePage extends Controller {

    private FileSystem fileSystem;

    public FilePage(FileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }

    @Override
    public HTTPResponse get(HTTPRequest request) {
        return new HTTPResponse(OK, fileSystem.readContentFor(request.getPath()));
    }

    @Override
    public HTTPResponse patch(HTTPRequest request) {
        if (fileHasNotChanged(request)) {
            fileSystem.writeTo(request.getBody(), request.getPath());
            return new HTTPResponse(NO_CONTENT);
        }
        return new HTTPResponse(PRECONDITION_FAILED);
    }

    protected boolean fileHasNotChanged(HTTPRequest request) {
        return etagValue(request).equals(toSha1(fileSystem.readContentFor(request.getPath()).getBytes()));
    }

    private String toSha1(byte[] fileContent) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return toHex(messageDigest.digest(fileContent));
    }

    private static String toHex(byte[] b) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            result.append(Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1));
        }
        return result.toString();
    }

    private String etagValue(HTTPRequest request) {
        return request.getHeaders().get("If-Match").trim();
    }
}

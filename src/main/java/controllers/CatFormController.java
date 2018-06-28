package controllers;

import controllers.fileSystem.FileSystem;
import request.HTTPRequest;
import response.HTTPResponse;

import static response.StatusLine.*;

public class CatFormController extends Controller {

    private FileSystem fileSystem;

    public CatFormController(FileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }

    @Override
    public HTTPResponse get(HTTPRequest request) {
        if (fileSystem.fileDoesNotExist(request.getLastPathSegment())) {
            return new HTTPResponse(NOT_FOUND);
        } else {
          return new HTTPResponse(OK, fileSystem.readContentFor(request.getLastPathSegment()));
        }
    }

    @Override
    public HTTPResponse post(HTTPRequest request) {
        fileSystem.writeTo(request.getBody(), createFormPath(request));
        return new HTTPResponse(CREATED, String.format("Location: /cat-form%s", createFormPath(request)), "");
    }

    @Override
    public HTTPResponse put(HTTPRequest request) {
        fileSystem.writeTo(request.getBody(), request.getLastPathSegment());
        return new HTTPResponse(OK);
    }

    @Override
    public HTTPResponse delete(HTTPRequest request) {
        fileSystem.deleteFile(request.getLastPathSegment());
        return new HTTPResponse(OK);
    }

    private String createFormPath(HTTPRequest request) {
        StringBuilder formPath = new StringBuilder("/");
        formPath.append(request.getBody().split("=")[0]);
        return formPath.toString();
    }
}

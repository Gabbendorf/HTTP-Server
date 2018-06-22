package controllers;

import controllers.fileSystem.FileSystem;
import request.HTTPRequest;
import response.HTTPResponse;

import static response.StatusLine.OK;

public class HomePage extends Controller {

    private FileSystem fileSystem;

    public HomePage(FileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }

    @Override
    public HTTPResponse get(HTTPRequest request) {
        return new HTTPResponse(OK, fileSystem.rootContent());
    }

    @Override
    public HTTPResponse head(HTTPRequest request) {
        return new HTTPResponse(OK);
    }
}

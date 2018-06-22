package controllers.fileSystem;

import request.HTTPRequest;

public class FileControllerFake extends FileController {

    private String etag;

    FileControllerFake(FileSystem fileSystem) {
        super(fileSystem);
    }

    @Override
    public boolean fileHasNotChanged(HTTPRequest request) {
        return (request.getHeaders().get("If-Match").trim().equals(etag));
    }

    public void setEtag(String etagToSet) {
        etag = etagToSet;
    }
}

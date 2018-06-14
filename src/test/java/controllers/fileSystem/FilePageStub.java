package controllers.fileSystem;

import request.HTTPRequest;

public class FilePageStub extends FilePage {

    private String etag;

    FilePageStub(FileSystem fileSystem) {
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

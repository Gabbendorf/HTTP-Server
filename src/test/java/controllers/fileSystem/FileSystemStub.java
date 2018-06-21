package controllers.fileSystem;

class FileSystemStub extends FileSystem {

    private String content = "ciao";

    @Override
    public String readContentFor(String path) {
        return content;
    }

    @Override
    public void writeTo(String content, String path) {
        this.content = content;
    }
}
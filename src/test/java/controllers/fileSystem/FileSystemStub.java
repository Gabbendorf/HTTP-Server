package controllers.fileSystem;

class FileSystemStub extends FileSystem {

    private String content = "ciao";

    public FileSystemStub(String directory) {
        super(directory);
    }

    @Override
    public String readContentFor(String path) {
        return content;
    }

    @Override
    public void writeTo(String content, String path) {
        this.content = content;
    }
}

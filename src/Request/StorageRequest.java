package Request;

public class StorageRequest {
    private String bucket;
    private String key;
    private String operation;
    private String fileName;
    private Integer fileSize;

    public String getFileName() {
        return fileName;
    }

    public String getOperation() {
        return operation;
    }

    public static class Builder {
        private String bucket;
        private String key;
        private String operation;
        private String fileName;
        private Integer fileSize;

        public Builder(String bucket, String key, String operation) {
            this.bucket = bucket;
            this.key = key;
            this.operation = operation;
        }

        public Builder fileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public Builder fileSize(Integer fileSize) {
            this.fileSize = fileSize;
            return this;
        }

        public StorageRequest build() {
            return new StorageRequest(this);
        }
    }

    public StorageRequest(Builder builder) {
        this.bucket = builder.bucket;
        this.key = builder.key;
        this.operation = builder.operation;
        this.fileName = builder.fileName;
        this.fileSize = builder.fileSize;
    }
}

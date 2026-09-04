package Request;

public class StorageRequest {
    private final String bucket;
    private final String key;
    private final String operation;
    private final String content_type;
    private final Long size;
    private final String encryption;

    private StorageRequest(Builder builder) {
        this.bucket = builder.bucket;
        this.key = builder.key;
        this.operation = builder.operation;
        this.content_type = builder.content_type;
        this.size = builder.size;
        this.encryption = builder.encryption;
    }

    public String getKey() {
        return key;
    }

    public String getOperation() {
        return operation;
    }

    public String getContent_type() {
        return content_type;
    }

    public Long getSize() {
        return size;
    }

    public String getEncryption() {
        return encryption;
    }

    public static class Builder {
        private String bucket;
        private String key;
        private String operation;
        private String content_type;
        private Long size;
        private String encryption;

        public Builder(String bucket, String key, String operation) {
            this.bucket = bucket;
            this.key = key;
            this.operation = operation;
        }

        public Builder content_type(String type) {
            this.content_type = type;
            return this;
        }
        public Builder size(Long size) {
            this.size = size;
            return this;
        }
        public Builder encryption(String encryption) {
            this.encryption = encryption;
            return this;
        }

        public StorageRequest build () {
            return new StorageRequest(this);
        }
    }
}

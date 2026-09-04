package Storage;

import Request.StorageRequest;

public interface StorageClient {
    void upload (StorageRequest req);
    void download (StorageRequest req);
}

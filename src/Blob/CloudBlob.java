package Blob;

import Logger.CloudLogger;
import Request.StorageRequest;

public interface CloudBlob {
    void upload(StorageRequest request, CloudLogger logger);
    StorageRequest download(StorageRequest request, CloudLogger logger);
}

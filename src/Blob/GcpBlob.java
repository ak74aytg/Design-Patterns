package Blob;

import Logger.CloudLogger;
import Logger.GcpLogger;
import Request.StorageRequest;

public class GcpBlob implements CloudBlob{
    @Override
    public void upload(StorageRequest request, CloudLogger logger) {
        logger.log(request);
    }

    @Override
    public StorageRequest download(StorageRequest request, CloudLogger logger) {
        logger.log(request);
        return request;
    }
}

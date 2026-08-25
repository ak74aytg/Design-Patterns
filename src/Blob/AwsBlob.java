package Blob;

import Logger.AwsLogger;
import Logger.CloudLogger;
import Request.StorageRequest;

public class AwsBlob implements CloudBlob {
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

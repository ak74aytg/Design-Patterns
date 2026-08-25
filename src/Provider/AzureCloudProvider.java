package Provider;

import Blob.AzureBlob;
import Blob.CloudBlob;
import Logger.AzureLogger;
import Logger.CloudLogger;
import Request.StorageRequest;

public class AzureCloudProvider implements CloudProvider{


    @Override
    public CloudBlob blob() {
        return new AzureBlob();
    }

    @Override
    public CloudLogger logger() {
        return new AzureLogger();
    }
}

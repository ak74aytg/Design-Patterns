package Provider;

import Blob.CloudBlob;
import Blob.GcpBlob;
import Logger.CloudLogger;
import Logger.GcpLogger;
import Request.StorageRequest;

public class GcpCloudProvider implements CloudProvider{


    @Override
    public CloudBlob blob() {
        return new GcpBlob();
    }

    @Override
    public CloudLogger logger() {
        return new GcpLogger();
    }
}

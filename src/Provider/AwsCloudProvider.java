package Provider;

import Blob.AwsBlob;
import Blob.CloudBlob;
import Logger.AwsLogger;
import Logger.CloudLogger;
import Request.StorageRequest;

public class AwsCloudProvider implements CloudProvider {


    @Override
    public CloudBlob blob() {
        return new AwsBlob();
    }

    @Override
    public CloudLogger logger() {
        return new AwsLogger();
    }
}

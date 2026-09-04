package Factory;

import Logger.AwsLoggerClient;
import Logger.LoggerClient;
import Storage.AwsStorageClient;
import Storage.StorageClient;

public class AwsCloudFactory implements CloudFactory{
    @Override
    public StorageClient getStorageClient() {
        return new AwsStorageClient();
    }

    @Override
    public LoggerClient getLoogerClient() {
        return new AwsLoggerClient();
    }
}

package Factory;

import Logger.AzureLoggerClient;
import Logger.LoggerClient;
import Storage.AzureStorageClient;
import Storage.StorageClient;

public class AzureCloudFactory implements CloudFactory{
    @Override
    public StorageClient getStorageClient() {
        return new AzureStorageClient();
    }

    @Override
    public LoggerClient getLoogerClient() {
        return new AzureLoggerClient();
    }
}

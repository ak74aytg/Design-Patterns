package Factory;

import Logger.GcpLoggerClient;
import Logger.LoggerClient;
import Storage.GcpStorageClient;
import Storage.StorageClient;

public class GcpCloudFactory implements CloudFactory{
    @Override
    public StorageClient getStorageClient() {
        return new GcpStorageClient();
    }

    @Override
    public LoggerClient getLoogerClient() {
        return new GcpLoggerClient();
    }
}

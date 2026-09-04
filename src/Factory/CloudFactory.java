package Factory;

import Logger.LoggerClient;
import Storage.StorageClient;

public interface CloudFactory {
    StorageClient getStorageClient();
    LoggerClient getLoogerClient();
}

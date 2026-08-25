package Logger;

import Request.StorageRequest;
import Config.ConfigManager;

public interface CloudLogger {
    void log(StorageRequest req);
}

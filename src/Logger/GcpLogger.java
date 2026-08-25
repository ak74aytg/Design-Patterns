package Logger;

import Config.ConfigManager;
import Request.StorageRequest;

public class GcpLogger implements CloudLogger {
    @Override
    public void log(StorageRequest req) {
        System.out.println("[GcpLogger] INFO: " + req.getOperation() + " " + req.getFileName() + " in " + ConfigManager.getRegion());
        System.out.println("[GcpLogger] INFO:" +  req.getOperation() + " Complete");
    }
}

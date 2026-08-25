package Logger;

import Request.StorageRequest;
import Config.ConfigManager;

public class AwsLogger implements CloudLogger{
    @Override
    public void log(StorageRequest req) {
        System.out.println("[AwsLogger] INFO: " + req.getOperation() + " " + req.getFileName() + " in " + ConfigManager.getRegion());
        System.out.println("[AwsLogger] INFO:" +  req.getOperation() + " Complete");
    }
}

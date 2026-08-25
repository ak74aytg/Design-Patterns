package Logger;

import Request.StorageRequest;
import Config.ConfigManager;

public class AzureLogger implements CloudLogger{

    @Override
    public void log(StorageRequest req) {
        System.out.println("[AzureLogger] INFO: " + req.getOperation() + " " + req.getFileName() + " in " + ConfigManager.getRegion());
        System.out.println("[AzureLogger] INFO:" +  req.getOperation() + " Complete");
    }

}

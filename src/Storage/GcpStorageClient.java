package Storage;

import Config.ConfigManager;
import Request.StorageRequest;

public class GcpStorageClient implements StorageClient{
    @Override
    public void upload(StorageRequest req) {
        System.out.println("[GCP] " +
                req.getOperation() +
                " " +
                ConfigManager.getInstance().getProvider() + "/" +
                req.getKey() + " in " +
                ConfigManager.getInstance().getRegion()
        );
    }

    @Override
    public void download(StorageRequest req) {
        System.out.println("[GCP] " +
                req.getOperation() +
                " " +
                ConfigManager.getInstance().getProvider() + "/" +
                req.getKey() + " in " +
                ConfigManager.getInstance().getRegion()
        );
    }
}

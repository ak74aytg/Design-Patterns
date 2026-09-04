package Storage;

import Config.ConfigManager;
import Request.StorageRequest;

public class AwsStorageClient implements StorageClient{
    @Override
    public void upload(StorageRequest req) {
        System.out.println("[AWS S3] " +
                req.getOperation() +
                " " +
                ConfigManager.getInstance().getProvider() + "/" +
                req.getKey() + " in " +
                ConfigManager.getInstance().getRegion()
        );
    }

    @Override
    public void download(StorageRequest req) {
        System.out.println("[AWS S3] " +
                req.getOperation() +
                " " +
                ConfigManager.getInstance().getProvider() + "/" +
                req.getKey() + " in " +
                ConfigManager.getInstance().getRegion()
        );
    }
}

import Config.ConfigManager;
import Factory.CloudFactory;
import Request.StorageRequest;

public class Main {
    public static void main(String[] args) {
        ConfigManager config = ConfigManager.getInstance("gcp", "us-east-1", true);
        StorageRequest request = new StorageRequest.Builder("prod", "prod-32.png", "upload")
                .size(10000L)
                .content_type("image/jpeg")
                .encryption("sha-256")
                .build();

        CloudFactory factory = CloudFactoryProvider.getFactory(config.getProvider());
        factory.getLoogerClient().log("Uploading");
        factory.getStorageClient().upload(request);
        factory.getLoogerClient().log("Upload Complete");
    }
}
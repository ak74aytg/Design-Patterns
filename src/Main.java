import Config.ConfigManager;
import Factory.CloudFactory;
import Provider.CloudProvider;
import Request.StorageRequest;

import java.util.Objects;

public class Main {
    public static void main(String[] args) throws Exception {
        ConfigManager configManager = ConfigManager
                .createInstance("GCP", "us-east-1", true);

        StorageRequest request = new StorageRequest
                .Builder("testing", "good_key", "download")
                .fileName("test-file")
                .fileSize(10)
                .build();


        CloudProvider provider = CloudFactory.createProvider(ConfigManager.getProvider());

        if (Objects.equals(request.getOperation(), "download")) {
            provider.blob().download(request, provider.logger());
        }else{
            provider.blob().upload(request, provider.logger());
        }
    }
}
package Factory;

import Provider.AwsCloudProvider;
import Provider.AzureCloudProvider;
import Provider.CloudProvider;
import Provider.GcpCloudProvider;

public class CloudFactory {

    public static CloudProvider createProvider (String provider) throws Exception{
        return switch (provider) {
            case "AWS" -> new AwsCloudProvider();
            case "GCP" -> new GcpCloudProvider();
            case "azure" -> new AzureCloudProvider();
            default -> throw new IllegalArgumentException("This cloud provider is not available");
        };
    }


}

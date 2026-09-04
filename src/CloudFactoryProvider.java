import Factory.AwsCloudFactory;
import Factory.AzureCloudFactory;
import Factory.CloudFactory;
import Factory.GcpCloudFactory;

public class CloudFactoryProvider {
    static CloudFactory getFactory(String provider) {
        switch (provider) {
            case "gcp":
                System.out.println("[CloudApp using GCPCloudFactory]");
                return new GcpCloudFactory();
            case "azure":
                System.out.println("[CloudApp using AzureCloudFactory]");
                return new AzureCloudFactory();
            default:
                System.out.println("[CloudApp using AWSCloudFactory]");
                return new AwsCloudFactory();
        }
    }
}

import Factory.AwsCloudFactory;
import Factory.CloudFactory;
import Factory.GcpCloudFactory;

public class CloudFactoryProvider {
    static CloudFactory getFactory(String provider) {
        switch (provider) {
            case "gcp":
                System.out.println("[CloudApp using GCPCloudFactory]");
                return new GcpCloudFactory();
            default:
                System.out.println("[CloudApp using AWSCloudFactory]");
                return new AwsCloudFactory();
        }
    }
}

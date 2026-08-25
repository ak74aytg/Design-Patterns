package Provider;

import Blob.CloudBlob;
import Logger.CloudLogger;
import Request.StorageRequest;

public interface CloudProvider {
    CloudBlob blob();
    CloudLogger logger();
}

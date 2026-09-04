package Logger;

public class AwsLoggerClient implements LoggerClient{
    @Override
    public void log(String message) {
        System.out.println("[AWSLogger] INFO: "+message);
    }
}

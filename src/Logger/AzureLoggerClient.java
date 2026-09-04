package Logger;

public class AzureLoggerClient implements LoggerClient{
    @Override
    public void log(String message) {
        System.out.println("[AzureLogger] INFO: "+message);
    }
}

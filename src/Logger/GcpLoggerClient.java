package Logger;

public class GcpLoggerClient implements LoggerClient{
    @Override
    public void log(String message) {
        System.out.println("[GCPLogger] INFO: "+message);
    }
}

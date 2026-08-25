package Config;

public class ConfigManager {
    private static ConfigManager instance;
    private static String provider;
    private static String region;
    private static boolean debug;

    private ConfigManager(String provider, String region, Boolean debug){
        ConfigManager.provider = provider;
        ConfigManager.region = region;
        ConfigManager.debug = debug;
    }

    public static ConfigManager createInstance(String provider, String region, Boolean debug) {
        if (instance == null){
            synchronized (ConfigManager.class) {
                if (instance == null) {
                    instance = new ConfigManager(provider, region, debug);
                }
            }
        }
        return instance;
    }

    public static String getProvider() {
        return provider;
    }

    public static String getRegion() {
        return region;
    }

    public static boolean getDebug() {
        return debug;
    }
}

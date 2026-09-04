package Config;

public class ConfigManager {
    private final String provider;
    private final String region;
    private final Boolean debug;

    public String getProvider() {
        return provider;
    }

    public String getRegion() {
        return region;
    }

    public Boolean getDebug() {
        return debug;
    }

    private static volatile ConfigManager instance;

    private ConfigManager(String provider, String region, Boolean debug) {
        this.provider = provider;
        this.region = region;
        this.debug = debug;
    }

    public static ConfigManager getInstance(String provider, String region, Boolean debug) {
        if (null == instance) {
            synchronized (ConfigManager.class) {
                if (null == instance) {
                    instance = new ConfigManager(provider, region, debug);
                }
            }
        }
        System.out.println("Config: provider="+instance.getProvider()+", region="+instance.getRegion()+", debug="+instance.getDebug());
        System.out.println();
        return instance;
    }

    public static ConfigManager getInstance() {
        return instance;
    }
}

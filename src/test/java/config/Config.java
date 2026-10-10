package config;

public class Config {
    /**
     * Specify the browser and platform for test:
     * CHROME
     * MOZILLA
     */
    public static final String BROWSER = "chrome";

    public static final boolean CLEAR_COOKIES =
            ConfigLoader.getBoolean("clear.cookies", true);

    public static final boolean CLEAR_REPORT_DIR =
            ConfigLoader.getBoolean("clear.report.dir", true);

    public static final boolean HOLD_BROWSER_OPEN =
            ConfigLoader.getBoolean("hold.browser.open", false);
}
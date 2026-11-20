package common.core;

import config.TestConfig;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public final class DriverFactory {

    private DriverFactory() {
    }
    private static final Dimension WINDOW_SIZE = new Dimension(1920, 1080);
    public static WebDriver createDriver() {
        Browser browser = Browser.from(TestConfig.INSTANCE.browser());
        boolean headless = TestConfig.INSTANCE.headless();

        WebDriver driver = switch (browser) {
            case CHROME -> new ChromeDriver(chromeOptions(headless));
            case FIREFOX -> new FirefoxDriver(firefoxOptions(headless));
        };
        driver.manage().window().setSize(WINDOW_SIZE);
        Duration timeout = Duration.ofSeconds(TestConfig.INSTANCE.pageLoadTimeout());
        driver.manage().timeouts().pageLoadTimeout(timeout);


        return driver;
    }

    private static ChromeOptions chromeOptions(boolean headless) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        if (headless) {
            options.addArguments("--headless=new", "--window-size=1920,1080");
        }
        return options;
    }

    private static FirefoxOptions firefoxOptions(boolean headless) {
        FirefoxOptions options = new FirefoxOptions();
        if (headless) {
            options.addArguments("-headless", "--width=1920", "--height=1080");
        }
        return options;
    }
}

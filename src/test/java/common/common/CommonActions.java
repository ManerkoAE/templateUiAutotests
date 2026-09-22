package common.common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

import static common.common.Config.BROWSER_AND_PLATFORM;
import static constants.Constant.TimeoutVariables.IMPLISIT_WAIT;

public class CommonActions {
    public static WebDriver createDriver() {
        WebDriver driver = null;
        switch(BROWSER_AND_PLATFORM){
            case "CHROME_WINDOWS":
                //System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                // WebDriverManager сам скачает нужную версию ChromeDriver
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
                case "MOZILLA_MAC":
//System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
                    // WebDriverManager сам скачает нужную версию FireFoxDriver
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
break;
            default:
                Assertions.fail("INCORRECT BROWSER NAME"+BROWSER_AND_PLATFORM);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLISIT_WAIT));
        return driver;
    }


}

package tests.base;

import common.common.CommonActions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import common.pages.bank_loans.bank_loans_page;
import common.pages.base.BasePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.time.LocalDateTime;
import java.util.Objects;

import static common.common.Config.*;

@ExtendWith(Listener.class)
//@Execution(ExecutionMode.CONCURRENT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseTest {

    protected WebDriver driver;
    protected BasePage basePage;
    protected bank_loans_page bank_loans_page;
    public static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);

    @BeforeEach
    public void setUp() {
        driver = CommonActions.createDriver();
        basePage = new BasePage(driver);
        bank_loans_page = new bank_loans_page(driver);
    }

    /**
     * Очистка папки со скриншотами и аллур дирректории
     * */
static{
    LOGGER.info("START TIME:"+ LocalDateTime.now());
    LOGGER.info("Start clear reports dir: build/reports");
    File allureResults = new File("allure-results");
if(allureResults.isDirectory()) {
//    for(File f : allureResults.listFiles()){}
    for (File item : Objects.requireNonNull(allureResults.listFiles()))
        item.delete();
}
if(CLEAR_REPORT_DIR){
    File allureScreenshots = new File("build/reports/tests");
    for (File item : Objects.requireNonNull(allureScreenshots.listFiles()))
        item.delete();

}
    }

    @AfterEach
    void clearCookiesAndLocalStorage() {
        if (CLEAR_COOKIES && driver != null) {
            try {
                JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
                driver.manage().deleteAllCookies();
                javascriptExecutor.executeScript("window.sessionStorage.clear()");
            } catch (Exception e) {
                // Игнорируем
            }
        }
    }

    @AfterAll
    void close() {
        if (!HOLD_BROWSER_OPEN && driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                // Игнорируем
            }
        }
    }
}
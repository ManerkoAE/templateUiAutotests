package tests;

import common.core.DriverFactory;
import common.core.Listener;
import common.pages.bank.BankLoansPage;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import common.pages.BasePage;
import org.openqa.selenium.WebDriverException;
import java.io.File;
import java.time.LocalDateTime;
import config.Config;


@ExtendWith(Listener.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@Slf4j
public abstract class BaseTest {

    public WebDriver driver;
    protected BasePage basePage;
    protected BankLoansPage bankLoansPage;

      @BeforeEach
    public void setUp() {
        driver = DriverFactory.createDriver();
        basePage = new BasePage(driver);
        bankLoansPage = new BankLoansPage(driver);
    }

    @BeforeAll
    public static void clearReports() {
        log.info("START TIME:" + LocalDateTime.now());
        log.info("Start clear reports dir: target/allure-results");
        deleteDirContents("allure-results");
    }

    private static void deleteDirContents(String path) {
        File dir = new File(path);
        if (!dir.isDirectory()) {
            log.info("Директория {} не существует — пропускаю очистку", dir.getAbsolutePath());
            return;
        }
        File[] files = dir.listFiles();
        if (files == null) return;
        for (File item : files) {
            item.delete();
        }
    }

    @AfterEach
    void clearCookiesAndSessionStorage() {
        if (Config.INSTANCE.clearCookies() && driver != null) {
            try {
                JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
                driver.manage().deleteAllCookies();
                javascriptExecutor.executeScript("window.sessionStorage.clear()");
            } catch (WebDriverException wde) {
            log.debug("Не удалось очистить cookies/sessionStorage: {}", wde.getMessage());
        }
        }
    }
}

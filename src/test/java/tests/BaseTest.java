package tests;

import common.core.DriverFactory;
import common.core.Listener;
import common.pages.bank.BankLoansPage;
import io.qameta.allure.Step;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import common.pages.BasePage;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.time.LocalDateTime;

import static config.Config.*;

@ExtendWith(Listener.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public abstract class BaseTest {

    public WebDriver driver;
    protected BasePage basePage;
    protected BankLoansPage bankLoansPage;
    public static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);

    @BeforeEach
    public void setUp() {
        driver = DriverFactory.createDriver();
        basePage = new BasePage(driver);
        bankLoansPage = new BankLoansPage(driver);
    }

    @BeforeAll
    public static void clearReports() {
        LOGGER.info("START TIME:" + LocalDateTime.now());
        LOGGER.info("Start clear reports dir: target/allure-results");
        deleteDirContents("allure-results");
    }

    private static void deleteDirContents(String path) {
        File dir = new File(path);
        if (!dir.isDirectory()) {
            LOGGER.info("Директория {} не существует — пропускаю очистку", dir.getAbsolutePath());
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
        if (CLEAR_COOKIES && driver != null) {
            try {
                JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
                driver.manage().deleteAllCookies();
                javascriptExecutor.executeScript("window.sessionStorage.clear()");
            } catch (WebDriverException wde) {
            }
        }
    }
}

package tests;

import common.core.DriverFactory;
import common.core.Listener;
import common.pages.bank.BankLoansPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.io.File;

import config.Config;

@ExtendWith(Listener.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)

public abstract class BaseTest {

    public WebDriver driver;
    protected BankLoansPage bankLoansPage;

    @BeforeEach
    public void setUp() {
        driver = DriverFactory.createDriver();
        bankLoansPage = new BankLoansPage(driver);
    }

    @BeforeAll
    public static void clearReports() {
        deleteDirContents("allure-results");
    }

    private static void deleteDirContents(String path) {
        File dir = new File(path);
        if (!dir.isDirectory()) {
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

            }
        }
    }
}

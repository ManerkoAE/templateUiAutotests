package tests;

import common.core.AllureResultsCleaner;
import common.core.DriverFactory;
import common.core.DriverHolder;
import common.core.Listener;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;


@Epic("DemoQA Bank")
@Feature("Банковские операции")
@ExtendWith(Listener.class)
public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeAll
    public static void cleanAllureResultsOnce() {
        AllureResultsCleaner.cleanOnce();
    }

    @BeforeEach
    public void setUp() {
        driver = DriverFactory.createDriver();
        DriverHolder.set(driver);
    }
}

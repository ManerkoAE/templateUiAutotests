package tests;

import common.pages.LoginPage;
import config.Config;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;

@Slf4j
public class BaseAuthTest extends BaseTest {
    @BeforeEach
    @Override
    public void setUp() {
        super.setUp();
        log.info("Выполняем авторизацию перед тестом...");
        log.info("URL: {}", Config.INSTANCE.baseBankUrl());

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage();   // ← используем метод из BasePage
        loginPage.loginAs(Config.INSTANCE.bankLogin(), Config.INSTANCE.bankPassword());
        loginPage.waitForUrlContains("/bank");
        log.info("Авторизация выполнена успешно");
    }
}
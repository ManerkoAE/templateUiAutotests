package tests;

import common.pages.LoginPage;
import common.utils.SeleniumUtils;
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
        loginPage.open();   // ← используем метод из BasePage
        loginPage.loginAs(Config.INSTANCE.bankLogin(), Config.INSTANCE.bankPassword());
        SeleniumUtils.waitForUrlContains(driver, Config.INSTANCE.baseBankUrl());
        log.info("Авторизация выполнена успешно");
    }
}
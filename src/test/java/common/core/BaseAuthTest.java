package common.core;

import config.ConfigLoader;
import common.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;

public class BaseAuthTest extends BaseTest {

    @BeforeEach
    @Override
    public void setUp() {
        super.setUp();

        String login = ConfigLoader.get("BankLogin");
        String password = ConfigLoader.get("BankPassword");
        String baseUrl = ConfigLoader.get("BaseBankUrl");


        LOGGER.info("Выполняем авторизацию перед тестом...");
        LOGGER.info("URL: {}", baseUrl);
        LoginPage loginPage = new LoginPage(driver);

        driver.get(baseUrl + "/login");
        loginPage.loginAs(login, password);

        loginPage.waitForUrlContains("/bank");

        LOGGER.info("Авторизация выполнена успешно");
    }
}
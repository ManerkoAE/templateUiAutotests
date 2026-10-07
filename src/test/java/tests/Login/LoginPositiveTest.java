package tests.Login;

import config.ConfigLoader;
import common.pages.BankPage;
import common.pages.LoginPage;
import org.junit.jupiter.api.Test;
import common.core.BaseTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginPositiveTest extends BaseTest {

    @Test
    void userCanLoginWithValidCredentials() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        String login = ConfigLoader.get("BankLogin");
        String password = ConfigLoader.get("BankPassword");
        loginPage.loginAs(login, password);
        loginPage.waitForUrlContains("/bank");

        BankPage bankPage = new BankPage(driver);
        assertTrue(bankPage.isUserLoggedIn(),
                "Пользователь должен быть авторизован после ввода валидных данных");
    }
}
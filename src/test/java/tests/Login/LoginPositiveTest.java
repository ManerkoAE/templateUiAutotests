package tests.Login;

import config.ConfigLoader;
import common.pages.BankPage;
import common.pages.LoginPage;
import org.junit.jupiter.api.Test;
import tests.BaseTest;
import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(bankPage.isUserLoggedIn()).
                as("Пользователь должен быть авторизован после ввода валидных данных").
                isTrue();
    }
}
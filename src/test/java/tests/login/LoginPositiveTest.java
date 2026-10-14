package tests.login;

import common.pages.BankPage;
import common.pages.LoginPage;
import common.utils.SeleniumUtils;
import config.Config;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginPositiveTest extends BaseTest {

    @Test
    void userCanLoginWithValidCredentials() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        String login = Config.INSTANCE.bankLogin();
        String password = Config.INSTANCE.bankPassword();
        loginPage.loginAs(login, password);
        SeleniumUtils.waitForUrlContains(driver, "/bank");

        BankPage bankPage = new BankPage(driver);
        Allure.step("Проверка авторизации пользователя", () -> {
            assertThat(bankPage.isUserLoggedIn(Config.INSTANCE.bankEmail()))
                    .withFailMessage("Пользователь %s не авторизован", login)
                    .isTrue();
        });
    }
}
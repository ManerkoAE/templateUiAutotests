package tests.login;

import common.pages.BankPage;
import common.pages.LoginPage;
import common.utils.SeleniumUtils;
import config.Config;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.Test;
import tests.BaseAuthTest;

import static org.assertj.core.api.Assertions.assertThat;

public class LogOutPositiveTest extends BaseAuthTest {
    @Test
    void userCanLogOut() {
        BankPage bankPage = new BankPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        Allure.step("Пользователь должен быть авторизован после ввода валидных данных", () -> {
            assertThat(bankPage.isUserLoggedIn(Config.INSTANCE.bankEmail())).isTrue();
        });
        bankPage.logout();
        SeleniumUtils.waitForUrlContains(driver, Config.INSTANCE.baseBankUrl() + "/login");
        Allure.step("Проверка выхода пользователя из учетной записи", () -> {
            assertThat(loginPage.isLoginFormVisible()).isTrue();
        });
    }
}
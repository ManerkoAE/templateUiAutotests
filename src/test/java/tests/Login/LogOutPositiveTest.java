package tests.Login;

import common.pages.BankPage;
import common.pages.LoginPage;
import org.junit.jupiter.api.Test;
import tests.BaseAuthTest;

import static org.assertj.core.api.Assertions.assertThat;

public class LogOutPositiveTest extends BaseAuthTest {
    @Test
    void userCanLogOut() {
        BankPage bankPage = new BankPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        assertThat(bankPage.isUserLoggedIn()).
                as("Пользователь должен быть авторизован после ввода валидных данных").
                isTrue();

        bankPage.logout();

        bankPage.waitForUrlContains("/bank/login");
        assertThat(loginPage.isLoginFormVisible()).
                as("После выхода должна отображаться форма логина").
                isTrue();
        LOGGER.info("Пользователь успешно вышел из системы");
    }
}

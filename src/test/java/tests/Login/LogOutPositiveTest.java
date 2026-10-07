package tests.Login;

import common.pages.BankPage;
import common.pages.LoginPage;
import org.junit.jupiter.api.Test;
import common.core.BaseAuthTest;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogOutPositiveTest extends BaseAuthTest {
    @Test
    void userCanLogOut() {
        BankPage bankPage = new BankPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        assertTrue(bankPage.isUserLoggedIn(),
                "Пользователь должен быть авторизован после логина");

        bankPage.logout();

        bankPage.waitForUrlContains("/bank/login");
        assertTrue(loginPage.isLoginFormVisible(),
                "После выхода должна отображаться форма логина");

        LOGGER.info("Пользователь успешно вышел из системы");
    }
}

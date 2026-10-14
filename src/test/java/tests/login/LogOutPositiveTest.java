package tests.login;

import common.pages.BankPage;
import common.pages.LoginPage;
import common.utils.SeleniumUtils;
import config.Config;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import tests.BaseAuthTest;

import static org.assertj.core.api.Assertions.assertThat;
@Slf4j
public class LogOutPositiveTest extends BaseAuthTest {
    @Test
    void userCanLogOut() {
        BankPage bankPage = new BankPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        assertThat(bankPage.isUserLoggedIn(Config.INSTANCE.bankEmail()))
                .as("Пользователь должен быть авторизован после ввода валидных данных")
                .isTrue();

        bankPage.logout();
       SeleniumUtils.waitForUrlContains(driver, Config.INSTANCE.baseBankUrl()+"/login");
        assertThat(loginPage.isLoginFormVisible())
                .as("После выхода должна отображаться форма логина")
                .isTrue();
        log.info("Пользователь успешно вышел из системы");
    }
}
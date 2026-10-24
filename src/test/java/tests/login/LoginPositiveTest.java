package tests.login;

import common.pages.BankPage;
import common.pages.LoginPage;
import config.TestConfig;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

import static org.assertj.core.api.Assertions.assertThat;

@Story("Успешный вход в систему")
public class LoginPositiveTest extends BaseTest {

    @Test
    @Description("Проверка входа валидными credentials")
    @Severity(SeverityLevel.NORMAL)
    void userCanLoginWithValidCredentials() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        String login = TestConfig.INSTANCE.bankLogin();
        String password = TestConfig.INSTANCE.bankPassword();
        loginPage.loginAs(login, password);

        BankPage bankPage = new BankPage(driver);
        bankPage.waitUntilOpened();

        Allure.step("Проверка авторизации пользователя", () -> {
            assertThat(bankPage.isUserLoggedIn(TestConfig.INSTANCE.bankEmail()))
                    .withFailMessage("Пользователь %s не авторизован", login)
                    .isTrue();
        });
    }
}
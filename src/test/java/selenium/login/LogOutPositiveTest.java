package selenium.login;

import common.selenium.pages.BankPage;
import common.selenium.pages.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import selenium.BaseAuthTest;

import static org.assertj.core.api.Assertions.assertThat;

@Story("Выход из системы")
public class LogOutPositiveTest extends BaseAuthTest {

    @Test
    @Description("Проверка успешного выхода авторизованного пользователя")
    @Severity(SeverityLevel.NORMAL)
    void userCanLogOut() {
        BankPage bankPage = new BankPage(driver);
        bankPage.logout();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitUntilOpened();
    }
}
package tests.login;

import common.pages.LoginPage;
import common.utils.TestData;
import config.ExpectedMessage;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

import static org.assertj.core.api.Assertions.assertThat;

@Story("Ошибка входа при неверных credentials")
public class LoginNegativeTest extends BaseTest {

    @Test
    @Description("Проверка сообщения об ошибке при неверном логине/пароле")
    @Severity(SeverityLevel.NORMAL)
    void loginWithInvalidCredentialsShowsError() {

        String wrongLogin = TestData.randomLogin();
        String wrongPassword = TestData.randomPassword();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.loginAs(wrongLogin, wrongPassword);
        Allure.step("Проверка авторизации пользователя", () -> {
            assertThat(loginPage.getLoginErrorText()).withFailMessage("Должно быть сообщение об ошибке").
                    isEqualTo(ExpectedMessage.WRONG_CREDENTIALS.text());
        });
    }
}
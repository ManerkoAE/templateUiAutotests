package tests.registration;

import common.pages.LoginPage;
import common.pages.RegistrationPage;
import common.utils.TestData;
import config.ExpectedMessage;
import config.TestConfig;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

import static org.assertj.core.api.Assertions.assertThat;

@Story("Ошибка регистрации при занятом логине")
public class RegistrationNegativeTest extends BaseTest {

    @Test
    @Description("Проверка сообщения об ошибке при попытке зарегистрировать существующий логин")
    @Severity(SeverityLevel.NORMAL)
    void registrationWithInvalidCredentialsShowsError() {

        String firstName = TestData.randomFirstName();
        String lastName = TestData.randomLastName();
        String email = TestData.randomEmail();
        String wrongLogin = TestConfig.INSTANCE.bankLogin();
        String newPassword = TestData.randomPassword();
        String confirmNewPassword = TestData.confirmRandomPassword();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.clickOnRegistrationButton();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.waitUntilOpened();

        registrationPage.registerNewUser(firstName, lastName, email, wrongLogin, newPassword, confirmNewPassword);
        Allure.step("Проверка, занят ли логин пользователя", () -> {
            assertThat(registrationPage.getRegistrationErrorText()).isEqualTo(ExpectedMessage.USERNAME_ALREADY_TAKEN.text());
        });
    }
}
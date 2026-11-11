package selenium.registration;

import common.selenium.pages.BankPage;
import common.selenium.pages.LoginPage;
import common.selenium.pages.RegistrationPage;
import common.utils.TestData;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import selenium.BaseTest;

import static org.assertj.core.api.Assertions.assertThat;

@Story("Успешная регистрация нового пользователя")
public class RegistrationPositiveTest extends BaseTest {

    @Test
    @Description("Проверка регистрации валидными данными")
    @Severity(SeverityLevel.NORMAL)
    void userCanRegisterWithValidCredentials() {

        String firstName = TestData.randomFirstName();
        String lastName = TestData.randomLastName();
        String email = TestData.randomEmail();
        String newLogin = TestData.randomLogin();
        String newPassword = TestData.randomPassword();
        String confirmNewPassword = TestData.confirmRandomPassword();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.clickOnRegistrationButton();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.waitUntilOpened();
        registrationPage.registerNewUser(firstName, lastName, email,
                newLogin, newPassword, confirmNewPassword);

        BankPage bankPage = new BankPage(driver);
        bankPage.waitUntilOpened();

        Allure.step("Проверка регистрации нового пользователя", () -> {
            assertThat(bankPage.isUserLoggedIn(email))
                    .as("Пользователь %s зарегистрирован", newLogin)
                    .isTrue();
        });
    }
}
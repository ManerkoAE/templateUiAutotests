package tests.registration;

import common.pages.BankPage;
import common.pages.RegistrationPage;
import common.utils.SeleniumUtils;
import common.utils.TestData;
import config.Config;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

import static org.assertj.core.api.Assertions.assertThat;

public class RegistrationPositiveTest extends BaseTest {

    @Test
    void userCanRegisterWithValidCredentials() {

        String firstName = TestData.randomFirstName();
        String lastName = TestData.randomLastName();
        String email = TestData.randomEmail();
        String newLogin = TestData.randomLogin();
        String newPassword = TestData.randomPassword();
        String confirmNewPassword = TestData.confirmRandomPassword();

        RegistrationPage registrationPage = new RegistrationPage(driver);

        registrationPage.open();
        assertThat(registrationPage.getCurrentUrl())
                .as("Страница регистрации должна быть открыта")
                .isEqualTo(Config.INSTANCE.baseBankUrl() + "/register");

        registrationPage.registerNewUser(firstName, lastName, email,
                newLogin, newPassword, confirmNewPassword);

        SeleniumUtils.waitForUrlContains(driver, "/bank");

        BankPage bankPage = new BankPage(driver);
        Allure.step("Проверка регистрации нового пользователя", () -> {
            assertThat(bankPage.isUserLoggedIn(email))
                    .as("Пользователь %s зарегистрирован", newLogin)
                    .isTrue();
        });
    }
}
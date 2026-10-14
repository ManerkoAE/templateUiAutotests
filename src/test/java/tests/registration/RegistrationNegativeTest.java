package tests.registration;

import common.pages.RegistrationPage;
import common.utils.TestData;
import config.Config;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

import static config.Constant.ExpectedMessages.WRONG_USER_NAME_ERROR;
import static org.assertj.core.api.Assertions.assertThat;

public class RegistrationNegativeTest extends BaseTest {

    @Test
    void registrationWithInvalidCredentialsShowsError() {

        String firstName = TestData.randomFirstName();
        String lastName = TestData.randomLastName();
        String email = TestData.randomEmail();
        String wrongLogin = "testuser";
        String newPassword = TestData.randomPassword();
        String confirmNewPassword = TestData.confirmRandomPassword();


        RegistrationPage registrationPage = new RegistrationPage(driver);

        registrationPage.open();
        Allure.step("Проверка открытия страницы регистрации", () -> {
            assertThat(registrationPage.getCurrentUrl()).isEqualTo(Config.INSTANCE.baseBankUrl() + "/register");
        });

        registrationPage.registerNewUser(firstName, lastName, email, wrongLogin, newPassword, confirmNewPassword);
        Allure.step("Проверка, занят ли логин пользователя", () -> {
            assertThat(registrationPage.getRegistrationErrorText()).isEqualTo(WRONG_USER_NAME_ERROR);
        });
    }
}
